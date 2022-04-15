package jpabook.jpashop.controller;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("items/new")
    public String createForm(final Model model) {
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    @PostMapping("items/new")
    public String create(final BookForm bookForm) {
        final Book book = bookForm.toBook();

        itemService.saveItem(book);
        return "redirect:/";
    }

    @GetMapping("items")
    public String list(final Model model) {
        final List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/list";
    }

    @GetMapping("items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, final Model model) {
        final Book item = (Book) itemService.findOne(itemId);

        BookForm form = new BookForm();
        form.setId(item.getId());
        form.setAuthor(item.getAuthor());
        form.setName(item.getName());
        form.setIsbn(item.getIsbn());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());

        model.addAttribute("form", form);
        return "items/updateItemForm";
    }

    @PostMapping("items/{itemId}/edit")
    public String updateItem(@PathVariable String itemId, @ModelAttribute("form") BookForm form) {
        Book book = form.toBook();

        itemService.saveItem(book);
        return "redirect:/items";
    }
}
