package pl.bb.listaprodktow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.bb.listaprodktow.model.Product;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {

    private List<Product> productList = new ArrayList<>();


    @GetMapping("/add")
    public String addProd(@RequestParam String nazwa, @RequestParam int cena) {

        Product product = new Product(nazwa, cena);
        productList.add(product);
        return "redirect:/";
    }

    @GetMapping("/lista")
    @ResponseBody
    public String list() {
        String result = "";
        int price = 0;
        for (Product product : productList) {
            price += product.getPrice();
            result += "Nazwa " + product.getName() + " Cena " + product.getPrice() + "<br/>";
        }
        return result + price;
    }

    @GetMapping("/tabela")
    public String table(Model model) {
        model.addAttribute("productList", productList);
        int price = 0;
        for (Product product : productList) {
            price += product.getPrice();
        }
        model.addAttribute("price", price);
        return "Tabela";
    }
}
