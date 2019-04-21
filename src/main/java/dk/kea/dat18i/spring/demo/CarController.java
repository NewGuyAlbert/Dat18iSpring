package dk.kea.dat18i.spring.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class CarController {




    @Autowired
    private CarRepository carRepo;

    // Main page
    @GetMapping("/mycar")
    public String car(Model model){
        List<Car> carList = carRepo.findAllCars();
        model.addAttribute("cars",carList);
        model.addAttribute("carForm", new Car());

        return "show-car";
    }

    // Main page but after we add a new car
    @PostMapping("/mycar")
    public String handleCarAdd(@ModelAttribute Car car) {

        carRepo.addCars(car);

        return "redirect:/mycar";
    }

    // Method for deleting a car
    @RequestMapping(value = "/cars/delete/{index}")
    public String handleCarDelete(@PathVariable int index) {
        carRepo.deleteCar(index);
        return "redirect:/mycar";
    }

    // The page where you create a new car to switch with the one you want to edit
    @RequestMapping(value="/cars/edit/{index}")
    public String editCarView(@PathVariable int index, Model model) {
        Car car=new Car();
        List<Car> carList = carRepo.findAllCars();
        for (Car car1 : carList)
            if (car1.getId() == index)
                car = car1;

        car.setId(index);
        model.addAttribute("car", car);
        model.addAttribute("index", index);

        return "edit-car";
    }

    // Editing the car with the one created just above
    @RequestMapping(value="/cars/edit/{index}", method = RequestMethod.POST)
    public String handleCarEdit(@PathVariable int index, @ModelAttribute Car car) {

        // replace targeted car with car containing edited info
        // for some reason we lose the id, so we set it again

        car.setId(index);

        carRepo.editCar(car);

        return "redirect:/mycar";
    }



}
