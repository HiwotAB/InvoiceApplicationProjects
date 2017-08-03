package hiwotab.invoiceapplicationproject.Controller;

import hiwotab.invoiceapplicationproject.Models.Product;
import hiwotab.invoiceapplicationproject.repositories.ProductRepostory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class MainController {
    @Autowired
    ProductRepostory productRepostory;

    @GetMapping("/")
    public String showIndex(Model model)
    {
        String myMessage="Welcome to the Invoice App";
        model.addAttribute("message",myMessage);
        return "index";
    }
    @GetMapping("/addproduct")
    public String addProductForm(Model model)
    {
        model.addAttribute("newProduct", new Product());
        return "addproduct";
    }
    @PostMapping("/addproduct")
    public String addProductForm(@Valid @ModelAttribute("newProduct") Product product, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()) {
            return "addproduct";
        }
        productRepostory.save(product);
        return "result";
    }
     @GetMapping("/showproducts")
    public @ResponseBody String showAllProduct()
    {
       Iterable<Product>productslist=productRepostory.findAll();
       return productslist.toString();
       /*for(Product products:productslist)
       {
           System.out.println(products.getDescription());
       }*/
    }

}
