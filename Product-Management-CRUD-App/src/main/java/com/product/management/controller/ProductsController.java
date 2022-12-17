package com.product.management.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.product.management.entity.Products;
import com.product.management.repository.ProductsRepository;

@Controller
public class ProductsController {

	@Autowired
	private ProductsRepository productRepository;

	@GetMapping("/")
	public String home(Model model) {
		List<Products> listOfProducts = productRepository.findAll();
		model.addAttribute("listOfProducts", listOfProducts);

		return "index";
	}

	// add product form
	@GetMapping("/addProduct")
	public String addProduct() {
		return "add_products";
	}

	// save product
	@PostMapping("/saveProduct")
	public String saveProduct(@ModelAttribute Products products, HttpSession session) {
		System.out.println(products);
		productRepository.save(products);
		session.setAttribute("msg", "Product Added Successfully");
		return "redirect:/addProduct";
	}

	// Edit Product
	@GetMapping("/edit/{id}")
	public String editProduct(@PathVariable("id") long id, Model model) {

		Optional<Products> productById = productRepository.findById(id);
		Products product = productById.get();
		model.addAttribute("product", product);

		return "edit_products";
	}

	// Update product
	@PostMapping("/updateProduct")
	public String updateProduct(@ModelAttribute Products products, HttpSession session) {
		System.out.println(products);
		productRepository.save(products);
		session.setAttribute("msg", "Product Updated Successfully.");

		return "redirect:/";
	}

	// delete product
	@GetMapping("/delete/{id}")
	public String deleteProduct(@PathVariable long id, HttpSession session) {

		productRepository.deleteById(id);
		session.setAttribute("msg", "Product Deleted Successfully.");
		return "redirect:/";
	}

}
