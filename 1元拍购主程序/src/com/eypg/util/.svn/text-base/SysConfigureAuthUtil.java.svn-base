package com.eypg.util;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/applicationContext*.xml" })
@Repository
public class SysConfigureAuthUtil {

	@Test
	public void go() throws InterruptedException, IOException {
		String ip = ApplicationListenerImpl.sysConfigureJson.getAuthorization();
		Document document = Jsoup.connect("http://121.40.156.25/authorization.txt").timeout(60000).get();
		if (StringUtil.isNotBlank(ip)) {
			if (document.text().indexOf(ip) != -1) {
				ApplicationListenerImpl.sysConfigureAuth = true;
			} else {
				ApplicationListenerImpl.sysConfigureAuth = false;
			}
		}
	}
	
		 
    private static final void printIp() { 
        try { 
            for (Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces(); e.hasMoreElements();) { 
                NetworkInterface item = e.nextElement(); 
 
                System.out.println(item.toString()); 
                System.out.println(item.getMTU() + " " + item.isLoopback() + " " + item.isPointToPoint() + " " + item.isUp() + " " + item.isVirtual()); 
 
                for (InterfaceAddress address : item.getInterfaceAddresses()) { 
                    if (address.getAddress() instanceof Inet4Address) { 
                        Inet4Address inet4Address = (Inet4Address) address.getAddress(); 
                        System.out.println(inet4Address.getHostAddress()); 
                        System.out.println(inet4Address.isLinkLocalAddress() + " " + inet4Address.isLoopbackAddress() + " " + inet4Address.isMCGlobal() + " " + inet4Address.isMulticastAddress()); 
                    } 
                } 
            } 
        } catch (IOException ex) { 
 
        } 
    } 

	public static void main(String[] args) throws SocketException, UnknownHostException {
		
		printIp();
//		System.out.println("192.168.0.106对应网卡的MAC是:");
//		NetworkInterface ne = NetworkInterface.getByInetAddress(InetAddress.getByName("192.168.0.106"));
//		byte[] mac = ne.getHardwareAddress();
//		System.err.println(mac[0]);
//		String mac_s = CHexConverUtil.(mac[0]) + ":" + hexByte(mac[1]) + ":"
//				+ hexByte(mac[2]) + ":" + hexByte(mac[3]) + ":"
//				+ hexByte(mac[4]) + ":" + hexByte(mac[5]);
//		System.out.println(mac_s);
	}

}
