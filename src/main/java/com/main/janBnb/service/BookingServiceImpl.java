package com.main.janBnb.service;
import com.main.janBnb.entity.Booking;
import com.main.janBnb.entity.Property;
import com.main.janBnb.entity.User;
import com.main.janBnb.exception.BookingNotFoundException;
import com.main.janBnb.exception.PropertyNotFoundException;
import com.main.janBnb.exception.RoomsAreNotAvailable;
import com.main.janBnb.payload.BookingDto;
import com.main.janBnb.repository.BookingRepository;
import com.main.janBnb.repository.PropertyRepository;
import com.main.janBnb.util.EmailService;
import com.main.janBnb.util.PdfService;
import com.main.janBnb.util.SmsService;
import com.main.janBnb.util.WhatsAppService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class BookingServiceImpl implements BookingService{

    private BookingRepository bookingRepository;
    private PropertyRepository propertyRepository;
    private EmailService emailService;
    private SmsService smsService;
    private WhatsAppService whatsAppService;
    private PdfService pdfService;
    private S3Service s3Service;

    public BookingServiceImpl(BookingRepository bookingRepository, PropertyRepository propertyRepository, EmailService emailService, SmsService smsService, WhatsAppService whatsAppService, PdfService pdfService, S3Service s3Service) {
        this.bookingRepository = bookingRepository;
        this.propertyRepository = propertyRepository;
        this.emailService = emailService;
        this.smsService = smsService;
        this.whatsAppService = whatsAppService;
        this.pdfService = pdfService;
        this.s3Service = s3Service;
    }

    @Override
    public BookingDto createBooking(BookingDto dto, String propertyId, User user) throws IOException {
         Property property = propertyRepository.findById(propertyId).orElseThrow(
                () -> new PropertyNotFoundException("Property Not Found")
        );
         Booking booking = bookingDtoToEntity(dto, property, user);
         Booking savedBooking = bookingRepository.save(booking);
         BookingDto bookingDto = bookingEntityToDto(savedBooking);
         pdfService.generatePdf(savedBooking,"D:\\Actual Main Project\\main-airbnb\\tickets\\"+savedBooking.getId()+"-confirmation.pdf");
         MultipartFile multipartFile = pdfService.convertFileToMultipartFile("D:\\Actual Main Project\\main-airbnb\\tickets\\" + savedBooking.getId() + "-confirmation.pdf");
         String pdfUrl = s3Service.uploadFile(multipartFile);
        emailService.sendSimpleEmail(savedBooking.getEmail(),"Booking Confirmation Mail","Your booking is confirmed click here....." + pdfUrl);
         smsService.sendSms(savedBooking.getMobile(),"Your booking is confirmed click here....." + pdfUrl);
         whatsAppService.sendWhatsappMessage(savedBooking.getMobile(),"Your booking is confirmed click here....." + pdfUrl);
         return bookingDto;
    }

    @Override
    public String deleteBooking(String bookingId) {
         Booking booking = bookingRepository.findById(bookingId).orElseThrow(
                () -> new BookingNotFoundException("Booking for id " + bookingId + " not found")

        );
         String msg = s3Service.deleteImage(booking.getId() + "-confirmation.pdf");
         bookingRepository.deleteById(booking.getId());
        return msg;
    }

    private BookingDto bookingEntityToDto(Booking savedBooking) {
        BookingDto booking = new BookingDto();
        booking.setId(savedBooking.getId());
        booking.setName(savedBooking.getName());
        booking.setEmail(savedBooking.getEmail());
        booking.setPropertyName(savedBooking.getProperty().getPropertyName());
        booking.setGst(savedBooking.getGst());
        BigDecimal totalPrice = BigDecimal.valueOf(savedBooking.getTotalPrice());
        totalPrice = totalPrice.setScale(2, RoundingMode.HALF_UP);
        booking.setTotalPrice(totalPrice.doubleValue());
        booking.setMobile(savedBooking.getMobile());
        booking.setNoOfNights(savedBooking.getNoOfNights());
        booking.setNoOfRooms(savedBooking.getNoOfRooms());
        booking.setLocalDateTime(savedBooking.getDateTime());
        return booking;
    }

    private Booking bookingDtoToEntity(BookingDto dto, Property property, User user) {
        if (property.getAvailableRooms() == 0) {
            throw new RoomsAreNotAvailable("Rooms Not Available");
        }
        Booking booking = null;
        if (property.getAvailableRooms() >= dto.getNoOfRooms()) {
           property.setAvailableRooms(property.getAvailableRooms() - dto.getNoOfRooms());
             booking = new Booking();
             booking.setNoOfRooms(dto.getNoOfRooms());
            booking.setId(UUID.randomUUID().toString());
            booking.setName(dto.getName());
            booking.setEmail(dto.getEmail());
            booking.setUser(user);
            Double price = dto.getNoOfNights() * property.getPrice() * dto.getNoOfRooms();
            Double gst = price * 0.18;
            booking.setGst(gst);
            booking.setTotalPrice(gst + price);
            booking.setProperty(property);
            booking.setMobile(dto.getMobile());
            booking.setNoOfNights(dto.getNoOfNights());
            booking.setDateTime(LocalDateTime.now());

        } else if (property.getAvailableRooms() <= dto.getNoOfRooms()) {
            System.out.println("this much rooms are available "+ property.getAvailableRooms()+ " are available.");
            int roomBooked = property.getAvailableRooms();
            property.setAvailableRooms(0);
            booking = new Booking();
            booking.setNoOfRooms(roomBooked);
            booking.setId(UUID.randomUUID().toString());
            booking.setName(dto.getName());
            booking.setEmail(dto.getEmail());
            booking.setUser(user);
            Double price = dto.getNoOfNights() * property.getPrice() * roomBooked;
            Double gst = price * 0.18;
            booking.setGst(gst);
            booking.setTotalPrice(gst + price);
            booking.setProperty(property);
            booking.setMobile(dto.getMobile());
            booking.setNoOfNights(dto.getNoOfNights());
            booking.setDateTime(LocalDateTime.now());
        }
        return booking;

    }
}
