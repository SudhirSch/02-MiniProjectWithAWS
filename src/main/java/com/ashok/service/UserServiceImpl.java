package com.ashok.service;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ashok.binding.LoginForm;
import com.ashok.binding.UnlockForm;
import com.ashok.binding.UserRegForm;
import com.ashok.entity.City;
import com.ashok.entity.Country;
import com.ashok.entity.Mail;
import com.ashok.entity.State;
import com.ashok.entity.User;
import com.ashok.repository.CityRepository;
import com.ashok.repository.CountryRepository;
import com.ashok.repository.StateRepository;
import com.ashok.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private StateRepository stateRepository;
	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public String saveUser(User user) {

		if (emailCheck(user.getUserEmail())) {
			userRepository.save(user);
			return "Successfully save";
		} else {
			return "please enter unique email address";
		}
	}

	@Override
	public List<User> getAllUser() {

		List<User> all = userRepository.findAll();
		return all;
	}

	@Override
	public String loginCheck(LoginForm loginFrom) {
		String email = loginFrom.getEmail();
		User user = userRepository.getUser(email);
		if(user==null) {
			return "please enter currect email and password";
		}
		if (loginFrom.getEmail().equals(user.getUserEmail()) && loginFrom.getPassword().equals(user.getUserPwd())) {
			return "login Successfully";
		} else {
			return "Please enter Currect email and pwd";
		}
	}

	@Override
	public boolean emailCheck(String email) {
		System.out.println(email);
		User user = userRepository.getUser(email);
		System.out.println(user);
		if (user == null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Map<Integer, String> loadCountry() {
		Map<Integer, String> countryMap = new HashMap<>();
		List<Country> all = countryRepository.findAll();
		all.forEach(a -> countryMap.put(a.getCountryId(), a.getCountryName()));
		System.out.println(countryMap);
		return countryMap;
	}

	@Override
	public Map<Integer, String> loadState(int countryId) {
		Map<Integer, String> stateMap = new HashMap<>();
		List<State> all = stateRepository.findByCountryId(countryId);
		all.forEach(a -> stateMap.put(a.getStateId(), a.getStateName()));
		System.out.println(stateMap);
		return stateMap;
	}

	@Override
	public Map<Integer, String> loadCity(int stateId) {

		Map<Integer, String> cityMap = new HashMap<>();
		List<City> all = cityRepository.findByStateId(stateId);
		all.forEach(a -> cityMap.put(a.getCityId(), a.getCityname()));
		System.out.println(cityMap);
		return cityMap;
	}

	@Override
	public String unlockAcc(UnlockForm unlockForm) {
		User user = userRepository.getUser(unlockForm.getEmail());
		if (unlockForm.getTempPassword().equals(user.getUserPwd())) {
			if (unlockForm.getNewPassword().equals(unlockForm.getConPassword())) {
				userRepository.updatePassword(unlockForm.getNewPassword(), unlockForm.getEmail());
				// userRepository.updatePassword(unlockForm.getNewPassword(),,unlockForm.getEmail());
				return "password update successfully";
			} else {
				return "please enter both passwords are not same";
			}
		} else {
			return "Teampary Password not correct";
		}
	}

	@Override
	public String forgotPwd(String email) {
		System.out.println(email);
		User user = userRepository.getUser(email);
		if (emailCheck(email)) {
			return "plz Enter currect email";
		} else {
			Mail mail = new Mail();
			mail.setMailFrom("bhosalesudhir468@gmail.com");
			mail.setMailTo(email);
			mail.setMailSubject("Password IES Account");
			mail.setMailContent("\r\n This is user\r\nPassword :- " + user.getUserPwd());

			sendEmail(mail);
			return "password send to email";
		}

	}

	// Method to generate a random alphanumeric password of a specific length
	private String generateRandomPassword(int len) {
		// ASCII range – alphanumeric (0-9, a-z, A-Z)
		final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

		SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder();

		// each iteration of the loop randomly chooses a character from the given
		// ASCII range and appends it to the `StringBuilder` instance

		for (int i = 0; i < len; i++) {
			int randomIndex = random.nextInt(chars.length());
			sb.append(chars.charAt(randomIndex));
		}

		return sb.toString();
	}

	@Override
	public String save(UserRegForm form) {

		User user = new User();
		user.setUserPwd(generateRandomPassword(6));
		user.setAccStatus("locked");
		BeanUtils.copyProperties(form, user);
		if (emailCheck(user.getUserEmail())) {
			userRepository.save(user);
			Mail mail = new Mail();
			mail.setMailFrom("bhosalesudhir468@gmail.com");
			mail.setMailTo(form.getUserEmail());
			mail.setMailSubject("UnLock IES Account");
			mail.setMailContent("\r\n" + "Hi " + form.getFristName() + " " + form.getLastName()
					+ "<br>   welcome ti IES family,<br>your registration is almost complete.\r\n"
					+ "please unlock your account using below dwtails.\r\n" + " <br>   Temporary Password :" + user.getUserPwd()
					+ "\r\n"
					+ "<br><html><body><a href=\"http://localhost:4200/unlock\">Click Here To Unlock Account</a></body></html>");
			sendEmail(mail);
			return "Successfully save";
		} else {
			return "please enter unique email address";
		}

	}

	public void setMailInfo() {
	}

	public void sendEmail(Mail mail) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();

		try {

			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

			mimeMessageHelper.setSubject(mail.getMailSubject());
			mimeMessageHelper.setFrom(new InternetAddress(mail.getMailFrom(), "Sudhir"));
			mimeMessageHelper.setTo(mail.getMailTo());
			mimeMessageHelper.setText(mail.getMailContent(), true);
			// mimeMessageHelper.setText("<html><body><a
			// href=\"https://www.w3schools.com\">Visit W3Schools.com!</a></body></html>",
			// true);
			mailSender.send(mimeMessageHelper.getMimeMessage());

		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
