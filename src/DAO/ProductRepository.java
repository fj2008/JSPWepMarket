package DAO;

import java.util.ArrayList;
import java.util.List;

import DTO.Product;
import exception.DuplicateProductException;

public class ProductRepository {
	private List<Product> listOfProducts;
	{
		listOfProducts = new ArrayList<Product>();
	}
	
	private static ProductRepository productRepository;
	static {
		productRepository = new ProductRepository();
	}
	
	public static ProductRepository getInstance() {
		return productRepository;
	}
	
	private ProductRepository() {
		Product phone = new Product("P1234", "iPhone 6s", 800000);
		phone.setDescription("4.7-inch, 1334X750 Retina HD display, 8-megapixel iSight Camera");
		phone.setCategory("Smart Phone");
		phone.setManufacturer("Apple");
		phone.setUnitsInStock(1000);
		phone.setCondition("New");
		phone.setImagePath("P1234.jpg");
		
		Product notebook = new Product("P1235", "LG PC 노트북", 1500000);
		notebook.setDescription("13.3-inch, IPS LED display, 5rd Generation Intel Core processor");
		notebook.setCategory("Notebook");
		notebook.setManufacturer("LG");
		notebook.setUnitsInStock(1000);
		notebook.setCondition("Refurbished");
		notebook.setImagePath("P1235.jpg");
		
		Product tablet = new Product("P1236", "Galaxy Tab 5", 900000);
		tablet.setDescription("212.8*125.6*6.6mm, Super AMOLED display, Octa-Core processor");
		tablet.setCategory("Tablet");
		tablet.setManufacturer("Samsung");
		tablet.setUnitsInStock(10000);
		tablet.setCondition("Old");
		tablet.setImagePath("P1236.jpg");
		
		listOfProducts.add(phone);
		listOfProducts.add(notebook);
		listOfProducts.add(tablet);
	}
	
	public ArrayList<Product> getAllProducts() {
		return (ArrayList<Product>) listOfProducts;
	}
	
	public Product getProduct(String productId) {
		Product product = null;

		for(Product nthProduct : listOfProducts) {
			String nthProductId = nthProduct.getProductId();
			
			if(nthProductId.equals(productId)) {
				product = nthProduct;
				break;
			}
		}
		
		return product;
	}
	
	public void addProduct(Product product) throws DuplicateProductException {
		for(Product nthProduct : listOfProducts) {
			String nthProductId = nthProduct.getProductId();
			
			if(nthProductId.equals(product.getProductId())) {
				throw new DuplicateProductException(product.getProductId() + "媛� 以묐났�릺�뿀�쓬");
			}
		}
		
		listOfProducts.add(product);
	}
}






