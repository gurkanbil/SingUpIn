package business.concretes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Entities.concretes.User;
import business.abstracts.AuthService;
import dataAccess.abstracts.UserDao;

public class AuthManager implements AuthService{

		UserDao userDao;
	
		public AuthManager(UserDao userDao) {
		//super();
		this.userDao = userDao;      	}
	
	@Override
	public void register(User user, String[] mailList) {

		if(user.getFirstName().length()>=2 && user.getLastName().length()>=2 && user.getPassword().length()>=6)	{
			
			if (checkMail(user.geteMail()))	{
				
				for (String mail : mailList) {
					
					if (mail==user.geteMail()) {
						
						System.out.println("Sisteme kayitli E-Mail");
						return;
					}		
				}
				userDao.add(user);
				sendMail(user);
				validMail(false);	
				
			}else {
				
				System.out.println("Hatali E-Mail");			
			}			
			
		}else {
			
			if (user.getFirstName().length()<2) {
				
				System.out.println("isim En Az 2 Karakter icermelidir.");
				return;
				
			}else if (user.getLastName().length()<2) {
				
				System.out.println("Soyisminiz En Az 2 Karakter icermelidir.");
				return;
				
			}else if (user.getPassword().length()<6) {
				
				System.out.println("Parolaniz En Az 6 Karakter Olmali.");
				return;
				
			}else if (user.geteMail().length()<1) {
				
				System.out.println("Mailiniz En Az 6 Karakter Olmali.");
				return;				
			}
		}				
	}
	
	public void sendMail(User user) {
		
		System.out.println("E-Mail Adresinizi Dogrulamak icin" + " " + user.geteMail() + " " +  "Adresine Gönderilen Aktivasyon Linkine Tiklayin.");
	}
	
	public void validMail(boolean click) {
		
		if(click) {
			
			System.out.println("E-Mail Adresiniz  Dogrulandi.");
			
		}else {
			
			System.out.println("Yolunda Gitmeyen Bir seyler Oldu, Dogrulama Mailini Tekrar Almayi Dene.");
		}		
	}
	
	public boolean checkMail(String eMail) 	{
		
		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(eMail);
		
		if (matcher.matches()) {
			
			return true;
			
		}else {
			
			return false;
		}		

		
	}

	@Override
	public void login(User user, String[] mailList) {
		
		if (checkMail(user.geteMail())) {
			
			for (String mail : mailList) {
				
				if (mail==user.geteMail()) {
					
					if (user.getPassword().length()>=6) {
						
						System.out.println("Sisteme Giris Yapildi " +  "Hosgeldiniz " +  user.getFirstName() + " " + user.getLastName());
						return;
						
					}else {
						
						System.out.println("Yanlis sifre Girdiniz.");
						return;
					}					
				}		
			}
			
			System.out.println("E-Mail Sistemde Kayitli Degil");
			return;			
		}
		System.out.println("Hatali E-Mail");		
	}	

		
}
	
	


