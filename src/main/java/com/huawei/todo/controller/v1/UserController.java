package com.huawei.todo.controller.v1;

import com.huawei.todo.dto.v1.AuthorityDto;
import com.huawei.todo.dto.v1.ConfirmationTokenDto;
import com.huawei.todo.dto.v1.UserDto;
import com.huawei.todo.entity.Authority;
import com.huawei.todo.entity.ConfirmationToken;
import com.huawei.todo.entity.User;
import com.huawei.todo.mapper.v1.AuthorityMapper;
import com.huawei.todo.mapper.v1.ConfirmationTokenMapper;
import com.huawei.todo.mapper.v1.UserMapper;
import com.huawei.todo.repository.AuthorityRepository;
import com.huawei.todo.repository.ConfirmationTokenRepository;
import com.huawei.todo.repository.UserRepository;
import com.huawei.todo.service.EmailSenderService;
import org.mapstruct.factory.Mappers;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author sumutella
 * @time 5:43 PM
 * @since 12/15/2019, Sun
 */
@Controller
public class UserController {
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    private final ConfirmationTokenMapper confirmationTokenMapper = Mappers.getMapper(ConfirmationTokenMapper.class);
    private final AuthorityMapper authorityMapper = Mappers.getMapper(AuthorityMapper.class);

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final EmailSenderService emailSenderService;


    public UserController(UserRepository userRepository, AuthorityRepository authorityRepository, ConfirmationTokenRepository confirmationTokenRepository, EmailSenderService emailSenderService) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.emailSenderService = emailSenderService;
    }


    @GetMapping({"/register", ""})
    public String displayRegistration(Model model, UserDto userDto) {
        model.addAttribute("user", userDto);
        return "user/register";
    }

    @PostMapping("/register")
    public String registerUser( @ModelAttribute UserDto user, Model model) {

        User existingUser = userRepository.findByEmailIgnoreCase(user.getEmail());
        if(existingUser != null) {
            model.addAttribute("message","This email already exists!");
            return "user/error";
        }
        else {
            String password = "{noop}"+user.getPassword();
            user.setPassword(password);
            user.setEnabled(false);
            User savedUser = userRepository.save(userMapper.dtoToEntity(user));

            ConfirmationTokenDto confirmationTokenDto = new ConfirmationTokenDto(userMapper.entityToDto(savedUser));

            confirmationTokenRepository.save(confirmationTokenMapper.dtoToEntity(confirmationTokenDto));

            AuthorityDto authorityDto = new AuthorityDto();
            authorityDto.setAuthority("ROLE_UNVERIFIED");
            authorityDto.setUsername(savedUser.getUsername());

            authorityRepository.save(authorityMapper.dtoToEntity(authorityDto));


            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom("semirkurtt@gmail.com");
            mailMessage.setText("To confirm your account, please click here : "
                    +"http://localhost:8080/confirm-account?token="+confirmationTokenDto.getConfirmationToken());

            emailSenderService.sendEmail(mailMessage);

            model.addAttribute("emailId", user.getEmail());

            return "user/successfulRegistration";
        }

    }

    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public String confirmUserAccount(Model model, @RequestParam("token")String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {


            User user = userRepository.findByEmailIgnoreCase(token.getUser().getEmail());
            user.setEnabled(true);
            userRepository.save(user);

            Authority authority = authorityRepository.findByUsernameIgnoreCase(user.getUsername());
            authority.setAuthority("ROLE_VERIFIED");
            authorityRepository.save(authority);


            return "user/accountVerified";
        }
        else
        {
            model.addAttribute("message","The link is invalid or broken!");
            return "user/error";
        }
    }

    @GetMapping("/login")
    public String showLogin(){
        return "user/login";
    }



}
