package com.jc.util;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Formatter;
import java.util.Locale;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Slf4j
public class ServerUtil {
	
    private static String serverIp;
    private static String processId;
    private static String serverId;
    private static String serverPort;
    private static String serverOsName;
    private static String serverMac;
    private static String serverName;

    static {
		try {
			serverOsName = System.getProperty("os.name");
			initServerIP();
			initProcessId();
            serverId = serverIp + "_" + processId;
            initServerPort();
			initServerNameAndMac();
			log.debug("ServerUtil初始化完成. ServerIP:" + getServerIp()
					+ ", ProcessId:" + getProcessId() + ", ServerMac:" + getServerMac()
					+ ", ServerOs:" + getServerOs() + ", ServerName:" + getServerName());
		} catch (Exception e) {
            log.error("ServerUtil初始化出错", e);
		}
	}

	private static String initProcessId() {
	    String name = ManagementFactory.getRuntimeMXBean().getName();
	    return processId = name.split("@")[0];
    }
    
    public static String getServerMac() {
    	return serverMac;
    }
    
    public static String getServerName() {
        return serverName;
    }

    public static String getServerIp() {
        return serverIp;
    }

    public static String getServerOs() {
        return serverOsName;
    }

    public static String getProcessId() {
        return processId;
    }

    public static String getServerId() {
        return serverId;
    }

    public static String getServerPort() {
        return serverPort;
    }

    private static String initServerIP() {
        try {
            serverIp = InetAddress.getLocalHost().getHostAddress();
            if ((StringUtils.isEmpty(serverIp)) || ("127.0.0.1".equals(serverIp))) {
                Enumeration<NetworkInterface> netInterfaces = null;
                try {
                    netInterfaces = NetworkInterface.getNetworkInterfaces();
                    while (netInterfaces.hasMoreElements()) {
                        NetworkInterface ni = netInterfaces.nextElement();
                        Enumeration<InetAddress> ips = ni.getInetAddresses();
                        while (ips.hasMoreElements()) {
                            serverIp = ips.nextElement().getHostAddress();
                            if (!"127.0.0.1".equals(serverIp)) {
                                break;
                            }
                        }
                    }
                } catch (Exception e) {
                    serverIp = null;
                }
            }
        } catch (UnknownHostException e) {
            serverIp = null;
            log.error("", e);
        }
        return serverIp;
    }
    
    /**
     * 获取tomcat的端口，path需要以/结束，如果未取到值，则返回默认值80
     *
     * @param path
     * @return
     */
    private static String getTomcatPort(String path) {
        String filePath = path + "server.xml";
        String rtvValue = "80";
        try {
            File f = new File(filePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(f);
            NodeList nl = doc.getElementsByTagName("Connector");
            Node portNode = null;
            boolean breakFlag = false;
            for (int i = 0; i < nl.getLength(); i++) {
                NamedNodeMap nm = nl.item(i).getAttributes();
                for (int j = 0; j < nm.getLength(); j++) {
                    if (nm.item(j).getNodeName()
                            .equalsIgnoreCase("connectionTimeout")) {
                        portNode = nl.item(i);
                        breakFlag = true;
                        break;
                    }
                }
                if (breakFlag) {
                    break;
                }
            }
            NamedNodeMap nm = portNode.getAttributes();
            for (int j = 0; j < nm.getLength(); j++) {
                if (nm.item(j).getNodeName().equalsIgnoreCase("port")) {
                    rtvValue = nm.item(j).getNodeValue();
                    break;
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return rtvValue;
    }

	private static void initServerNameAndMac() throws UnknownHostException,
        SocketException {
		InetAddress addr = InetAddress.getLocalHost();
		serverName = addr.getHostName();

		NetworkInterface networkInterface = NetworkInterface
		        .getByInetAddress(addr);
		byte[] mac = networkInterface.getHardwareAddress();
		Formatter formatter = new Formatter();
		if(mac != null && mac.length > 0) {
			for (int i = 0; i < mac.length; i++) {
				formatMac(formatter, mac[i], i >= mac.length - 1);
			}
		}
		serverMac = formatter.toString();
	}

	/**
	 * F4-8C-50-0B-B2-D3
	 * @param formatter
	 * @param mac
	 * @return
	 */
	private static void formatMac(Formatter formatter, byte mac, boolean isLast){
		formatter.format(Locale.getDefault(), "%02X%s", mac, (isLast ? "" : "-"));
	}

	private static String initServerPort() {
		String path = System.getProperty("catalina.home");
		if (StringUtils.isEmpty(path)) {
            log.error("path is empty. server port init failure...");
			return null;
		}
		String os = getServerOs();
		if (os.indexOf("Windows") >= 0) {
			path = path + "/conf/";
		} else {
			path = path + "/conf/";
		}
		return serverPort = getTomcatPort(path);
	}

    public static void main(String[] args) {
        System.out.println(getProcessId());
        System.out.println(getServerId());
        System.out.println(getServerIp());
        System.out.println(getServerName());
        System.out.println(getServerOs());
        System.out.println(getServerPort());
        System.out.println(getServerMac());
    }
}
