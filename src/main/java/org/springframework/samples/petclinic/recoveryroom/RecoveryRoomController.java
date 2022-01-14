package org.springframework.samples.petclinic.recoveryroom;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/recoveryroom")
public class RecoveryRoomController {
    
    private static final String CREATE_OR_UPDATE_VIEW_RECOVERY_ROOM = "recoveryroom/createOrUpdateRecoveryRoomForm";

    @Autowired
    private RecoveryRoomService recoveryRoomService;

    @GetMapping(path = "/create")
    public String createForm(ModelMap modelMap) {
        modelMap.addAttribute("recoveryRoom", new RecoveryRoom());
        modelMap.addAttribute("roomTypeList", recoveryRoomService.getAllRecoveryRoomTypes());
        return CREATE_OR_UPDATE_VIEW_RECOVERY_ROOM;
    }

    @PostMapping(path = "/create")
    public String postForm(@Valid RecoveryRoom recoveryRoom, BindingResult result, ModelMap modelMap) {
        if (result.hasErrors()) {
            modelMap.addAttribute("recoveryRoom", recoveryRoom);
            modelMap.addAttribute("roomTypeList", recoveryRoomService.getAllRecoveryRoomTypes());
            return CREATE_OR_UPDATE_VIEW_RECOVERY_ROOM;
        } else {
            recoveryRoomService.save(recoveryRoom);
            modelMap.addAttribute("message", "Recovery room succesfully created");
            return "welcome";
        }
    }
}
