package edu.mum.pm.controller;

import edu.mum.pm.config.SessionListener;
import edu.mum.pm.domain.CustomerOrder;
import edu.mum.pm.domain.OrderDetail;
import edu.mum.pm.domain.Product;
import edu.mum.pm.service.OrderService;
import edu.mum.pm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Date;

@Controller
@RequestMapping("/cart")
@SessionAttributes({"myOrder"})
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private SessionListener session;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String myCart(ModelMap map, Model model, @ModelAttribute CustomerOrder customerOrder) {
        CustomerOrder myCustomerOrder = this.getCurrentOrder(map);
        addOrderToSession(map, myCustomerOrder);
        return "cart/index";
    }

    /***
     * Add product into my customerOrder
     * @param id
     * @param map
     * @return
     */
    @RequestMapping(value = "/add/product/{id}", method = RequestMethod.POST)
    public String addProduct(@PathVariable("id") int id, ModelMap map, Model model, @ModelAttribute CustomerOrder customerOrder, BindingResult result) {
        CustomerOrder myCustomerOrder = this.getCurrentOrder(map);
        Product product = productService.getProduct(id);
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProduct(product);
        orderDetail.setCustomerOrder(myCustomerOrder);
        orderDetail.setQuantity(1); // default
        myCustomerOrder.addOrderDetail(orderDetail);
        addOrderToSession(map, myCustomerOrder);
        return "redirect:/cart/";
    }

    @RequestMapping(value = "/remove/product/{id}", method = RequestMethod.GET)
    public String removeProduct(@PathVariable("id") int id, ModelMap map, Model model) {
        CustomerOrder myCustomerOrder = this.getCurrentOrder(map);
        myCustomerOrder.removeOrderDetail(id);
        addOrderToSession(map, myCustomerOrder);
        return "redirect:/cart/";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(ModelMap map, @ModelAttribute("myOrder") CustomerOrder customerOrder) {
        CustomerOrder myCustomerOrder = getCurrentOrder(map);
        // Update the new quantity for each product
        for (int i = 0; i < myCustomerOrder.getOrderDetails().size(); i++) {
            myCustomerOrder.getOrderDetails().get(i).setQuantity(customerOrder.getOrderDetails().get(i).getQuantity());
        }
        return "redirect:/cart/";
    }

    public CustomerOrder getCurrentOrder(ModelMap map) {
        CustomerOrder myCustomerOrder = (CustomerOrder) map.get("myCustomerOrder");
        if (myCustomerOrder == null) {
            myCustomerOrder = new CustomerOrder();
            map.addAttribute("myOrder", myCustomerOrder);
        }
        return myCustomerOrder;
    }

    public void addOrderToSession(ModelMap map, CustomerOrder customerOrder) {
        map.addAttribute("myOrder", customerOrder);
    }

    //	@RequestMapping(value="/submit", method=RequestMethod.GET)
//	public String getSubmit(ModelMap map, @ModelAttribute CustomerOrder customerOrder) {
//		CustomerOrder myOrder = this.getCurrentOrder(map);
//		addOrderToSession(map, myOrder);
//		return "/cart/index";
//	}
//	
    @RequestMapping(value = "/submit", method = RequestMethod.GET)
    public String submit(ModelMap map, @ModelAttribute("myOrder") CustomerOrder customerOrder) {
        CustomerOrder myCustomerOrder = getCurrentOrder(map);
        // Update the new quantity for each product
        for (int i = 0; i < myCustomerOrder.getOrderDetails().size(); i++) {
            myCustomerOrder.getOrderDetails().get(i).setQuantity(customerOrder.getOrderDetails().get(i).getQuantity());
        }
        myCustomerOrder.setOrderDate(new Date());
        myCustomerOrder.setCustomer(session.getPerson());
        orderService.save(myCustomerOrder);
        // Clear the customerOrder session
        map.addAttribute("myOrder", new CustomerOrder());
        return "redirect:/me/order";
    }


}
