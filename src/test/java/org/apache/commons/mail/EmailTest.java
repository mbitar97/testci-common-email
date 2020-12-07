package org.apache.commons.mail;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EmailTest {
	
	private static String[] emailList = {"email1@gmail.com", "email2@gmail.com", "email3@gmail.com", "email4@gmail.com", "email5@gmail.com"};
	private static String[] emptyEmailList = {};
	private EmailConcrete email;
	
	@Before
	public void setUpEmailTest() throws Exception
	{
		email = new EmailConcrete();
	}
	
	@After
	public void tearDownEmailTest() throws Exception
	{

	}
	
	@Test
	public void testAddBcc() throws Exception
	{
		email.addBcc(emailList);
		assertNotNull(email.getBccAddresses());
		assertEquals(5, email.getBccAddresses().size());	
	}
	
	@Test
	public void testAddCc() throws Exception
	{
		email.addCc("email@gmail.com");
		assertNotNull(email.getCcAddresses());
		assertEquals(1, email.getCcAddresses().size());	
	}
	
	@Test
	public void testAddHeader() throws Exception
	{
		String Headername = "headername";
		String Headervalue = "headervaluetxt";
		email.addHeader(Headername, Headervalue);
	}	
	@Test (expected = IllegalArgumentException.class)
	public void testAddHeaderNullVal() throws Exception{
		String Headername = "headername";
		email.addHeader(Headername, null);
	}
	@Test (expected = IllegalArgumentException.class)
	public void testAddHeaderNullName() throws Exception{
		String Headervalue = "headervaluetxt";
		email.addHeader(null, Headervalue);
	}
	
	@Test
	public void testAddReplyTo() throws Exception
	{
		String an_email = "email@gmail.com";
		String name = "name";
		email.addReplyTo(an_email, name);
		assertNotNull(email.getReplyToAddresses());
	}
	
	@Test 
	public void testBuildMimeMessage() throws Exception
	{
		String hostname = "hostname";
		String subj = "Message Subject";
		String content = "Content Type";
		String from = "email@gmail.com";
		String [] toEmails = emailList;
		String replyto = "another.email@gmail.com";
		String [] BccEmailList = emailList;
		String Headername = "headername";
		String Headervalue = "headervaluetxt";
		email.setHostName(hostname);
		email.setSubject(subj);
		email.updateContentType(content);		
		email.setFrom(from);	
		email.addTo(toEmails);	
		email.addReplyTo(replyto);
		email.addBcc(BccEmailList);
		email.addHeader(Headername, Headervalue);	
		email.setCharset("ASCII");	
		email.buildMimeMessage();
	}	
	@Test  (expected = IllegalStateException.class)
	public void testBuildMimeMessageTwice() throws Exception
	{
		String hostname = "hostName";
		email.setHostName(hostname);
		email.setFrom("email@gmail.com");
		email.addReplyTo("another.email@gmail.com");
		email.setTo(email.getReplyToAddresses());
		email.buildMimeMessage();
		email.buildMimeMessage();
	}	
	@Test (expected = EmailException.class)
	public void testBuildMimeMessageNullFrom() throws Exception
	{
		String host = "hostName";
		email.setHostName(host);
		email.addReplyTo("another.email@gmail.com");
		email.setTo(email.getReplyToAddresses());
		email.buildMimeMessage();
	}
	@Test (expected = EmailException.class)
	public void testBuildMimeMessageNullHostname() throws Exception
	{
		//String host = "hostName";
		//email.setHostName(host);
		email.setFrom("email@gmail.com");
		email.addReplyTo("another.email@gmail.com");
		email.setTo(email.getReplyToAddresses());
		email.buildMimeMessage();
	}	
	@Test (expected = EmailException.class)
	public void testBuildMimeMessageNullTo() throws Exception{
		String hostname = "HostName";
		email.setHostName(hostname);
		email.setFrom("email@gmail.com");
		email.addReplyTo("another.email@gmail.com");
		email.buildMimeMessage();
	}	
	
	@Test
	public void testGetHostName() throws Exception
	{
		String hostname = "hostname";
		email.setHostName(hostname);
		assertEquals(hostname, email.getHostName());
	}
	@Test (expected = EmailException.class)
	public void testGetHostNameNull() throws EmailException
	{
		String hostname = null;
		email.setHostName(hostname);
		assertNotNull(email.getHostName());
	}
	
	@Test
	public void testGetMailSession() throws Exception
	{
		String hostname = "Hostname";
		email.setHostName(hostname);
		email.setBounceAddress("email@gmail.com");
		String username = "username";
		String password = "password";
		email.setAuthentication(username, password);
		assertEquals(hostname, email.getHostName());
		assertNotNull(email.getBccAddresses());
		assertNotNull(email.getMailSession());
	}
	
	@Test
	public void testGetSentDate() throws Exception
	{
		Date a_date = new Date(1);
		email.setSentDate(a_date);
		assertNotNull(email.getSentDate());
		assertEquals(a_date, email.getSentDate());
	}
	
	@Test
	public void testGetSocketConnectonTimeout() throws Exception
	{
		int timeout = 2;
		email.setSocketConnectionTimeout(timeout);
		assertNotNull(email.getSocketConnectionTimeout());
		assertEquals(timeout, email.getSocketConnectionTimeout());
	}
	
	@Test
	public void testSetFrom() throws Exception
	{
		String an_email = "email@gmail.com";
		email.setFrom(an_email);
		assertEquals(an_email, email.getFromAddress().toString());
	}
	
}
