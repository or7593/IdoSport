package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Main extends Base {

	public Main(WebDriver driver) {
		super(driver);
	}

	// Change to English + verification
	public boolean changeEN() {
		click(By.linkText("English"));
		String text = getText(By.xpath("//a[@href='https://www.flowers-chen.co.il/en/product-cat/bouquets/']"));

		if (text.equals("Bouquets"))
			return true;

		else
			return false;
	}
	
	public boolean changeHE() {
		
		click(By.linkText("עברית"));
		
		return true;
	}

	public void selectFirstProduct() {
		click(By.xpath("//div[@id=\"content\"]/section[1]/div/ul/li[1]/div/a[1]/img"));
	}

}
