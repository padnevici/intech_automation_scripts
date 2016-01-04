package intech.automation.framework;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class Configs {
	private static Configs instance = null;
	private DocumentBuilderFactory factory = null;
	private DocumentBuilder builder = null;
	private Document docConfig = null;
	private Document docWebSitePaths = null;
	private XPathFactory xpathFactory = null;
	private XPath xpath = null;

	private static final Logger logger = LogManager.getLogger(Configs.class);

	protected Configs() {
		factory = DocumentBuilderFactory.newInstance();
		xpathFactory = XPathFactory.newInstance();
		factory.setNamespaceAware(true);
		xpath = xpathFactory.newXPath();

		try {
			builder = factory.newDocumentBuilder();
			docConfig = builder.parse("src/resources/Config.xml");
			docWebSitePaths = builder.parse("src/resources/WebSitePaths.xml");
		} catch (IOException e) {
			logger.error("Cannot find src/resources/Config.xml", e);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			logger.error("An error while reading Config.xml", e);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			logger.error("An error while reading Config.xml", e);
		}
	}

	public static Configs getInstance() {
		if (instance == null) {
			instance = new Configs();
		}
		return instance;
	}

	public String getUrl() {
		String url = "";

		try {
			XPathExpression expr = xpath.compile("configs/defaultURL");
			url = (String) expr.evaluate(docConfig, XPathConstants.STRING);
		} catch (XPathExpressionException e) {
			logger.error("An error while executing xpath query", e);
		}
		return url;
	}

	public String getBrowser() {
		String browserType = "";

		try {
			XPathExpression expr = xpath.compile("configs/mainBrowser");
			browserType = (String) expr.evaluate(docConfig, XPathConstants.STRING);
		} catch (XPathExpressionException e) {
			logger.error("An error while executing xpath query", e);
		}
		return browserType;
	}

	public long getImplicitWaitTime() {
		long time = 0;

		try {
			XPathExpression expr = xpath.compile("configs/implicitWaitMls");
			time = Long.parseLong((String) expr.evaluate(docConfig, XPathConstants.STRING));
		} catch (XPathExpressionException e) {
			logger.error("An error while executing xpath query", e);
		}
		return time;
	}

	public long getImplicitSeleniumWaitTime() {
		long time = 0;

		try {
			XPathExpression expr = xpath.compile("configs/implicitSeleniumWaitSec");
			time = Long.parseLong((String) expr.evaluate(docConfig, XPathConstants.STRING));
		} catch (XPathExpressionException e) {
			logger.error("An error while executing xpath query", e);
		}
		return time;
	}

	public static class WebSitePaths {
		public static class Insegment {
		}
	}
}
