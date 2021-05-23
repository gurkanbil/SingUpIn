package dataAccess.concretes;

import Entities.concretes.User;
import dataAccess.abstracts.UserDao;

public class HibernateUserDao implements UserDao{
	@Override
	public void add(User user) {
		System.out.println("Uyelik Olusturuldu.");
	}
}
