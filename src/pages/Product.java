package pages;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Product extends Base {

	Actions action;

	public Product(WebDriver driver) {
		super(driver);
	}

	public void selectDate() throws InterruptedException {

		click(By.id("shipping_delivery_date"));
		Thread.sleep(2000);
		List<WebElement> days = driver.findElements(By.xpath("//table[@id='grid-shipping_delivery_date']//td"));

		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int day   = localDate.getDayOfMonth();
		day = day + 1;

		for (WebElement e : days) {
			// System.out.println(e.getText());
			if (e.getText().equals(String.valueOf(day))) {
				e.click();
				break;
				// }

			}
		}
	}
	
	public boolean goToCart() {
		click(By.cssSelector(".single_add_to_cart_button"));
		click(By.partialLinkText("רוצה להמשיך לסל הקניות"));
		String text = getText(By.xpath("//*[@id=\"post-8\"]/header/h1"));
		
		if (text.equals("סל קניות"))
			return true;
		else
			return false;
		
	}

}
