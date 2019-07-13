/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.product;

import java.util.Collection;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

/**
 */
@Controller
class ProductController {
	private ProductRepository products;

	public ProductController(ProductRepository products) {
		super();
		this.products = products;
	}

	@GetMapping("/products")
	public String processFindForm(Model model) {
		Collection<Product> productsList = products.findAll();	
		model.addAttribute("products", productsList);
		return "products/productsList";
	}

	@GetMapping("/products/{productId}")
	public ModelAndView showProduct(@PathVariable("productId") int productId) {
        ModelAndView mav = new ModelAndView("products/productDetails");
        mav.addObject(this.products.findById(productId));
        return mav;		
	}
	
	
}
