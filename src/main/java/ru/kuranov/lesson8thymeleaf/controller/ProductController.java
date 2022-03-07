package ru.kuranov.lesson8thymeleaf.controller;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kuranov.lesson8thymeleaf.controller.util.FilterSolver;
import ru.kuranov.lesson8thymeleaf.entity.Product;
import ru.kuranov.lesson8thymeleaf.entity.dto.ProductNewDto;
import ru.kuranov.lesson8thymeleaf.entity.mapper.ProductMapper;
import ru.kuranov.lesson8thymeleaf.service.ProductService;

import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class ProductController {

    private final ProductService productService;
    private final FilterSolver filterSolver;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, FilterSolver filterSolver, ProductMapper productMapper) {
        this.productService = productService;
        this.filterSolver = filterSolver;
        this.productMapper = productMapper;
    }

    @GetMapping("/app/products/{id}")
    public String viewProduct(Model model, @PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "product-view";
        } else {
            return "404";
        }
    }

    @GetMapping("/app/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            productService.deleteById(id);
            filterSolver.resetFilter(true);
            return "redirect:/app/products";
        } else {
            return "404";
        }
    }

    @GetMapping("/app/products/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "product-edit";
        } else {
            return "404";
        }
    }

    @PostMapping("/app/products/edit/{id}")
    public String editProduct(@Valid ProductNewDto productNewDto,
                              BindingResult bindingResult,
                              RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            attributes.addFlashAttribute("errors", getErrors(bindingResult));
            attributes.addFlashAttribute("id", productNewDto.getId());
            return "redirect:/app/products/edit/{id}";
        } else {
            saveProduct(productNewDto);
            return "redirect:/app/products";
        }
    }

    @GetMapping("/app/products/add")
    public String addProduct(Model model) {
        ProductNewDto productNewDto = ProductNewDto.builder().build();
        model.addAttribute("productNewDto", productNewDto);
        return "product-add";
    }

    @PostMapping("/app/products/add")
    public String addProduct(@Valid ProductNewDto productNewDto,
                             BindingResult bindingResult,
                             RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            attributes.addFlashAttribute("errors", getErrors(bindingResult));
            return "redirect:/app/products/add";
        } else {
            saveProduct(productNewDto);
            return "redirect:/app/products";
        }
    }

    private Set<String> getErrors(BindingResult bindingResult) {
        return bindingResult.getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toSet());
    }

    private void saveProduct(ProductNewDto productNewDto) {
        productService.save(productMapper.getProduct(productNewDto));
        filterSolver.resetFilter(true);
    }
}
