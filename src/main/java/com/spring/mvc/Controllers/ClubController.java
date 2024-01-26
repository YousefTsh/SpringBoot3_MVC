package com.spring.mvc.Controllers;

import com.spring.mvc.dto.ClubDto;
import com.spring.mvc.models.Club;
import com.spring.mvc.models.UserEntity;
import com.spring.mvc.models.batchEntity;
import com.spring.mvc.repositorys.BatchRepository;
import com.spring.mvc.repositorys.ClubRepository;
import com.spring.mvc.security.SecurityUtil;
import com.spring.mvc.services.ClubService;
import com.spring.mvc.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ClubController {
    private final ClubService clubService;
    private final UserService userService;
    private final BatchRepository batchRepository;

    @GetMapping("/clubs")
    public String ListClubs(Model model, @RequestParam(name = "test",required = false) String test){
        UserEntity user = new UserEntity();
        List<ClubDto> clubs = clubService.findAllClubs();
        String username = SecurityUtil.getSessionUser();
        if (username != null){
            user = userService.findByUsername(username);
            model.addAttribute("user",user);
        }
        model.addAttribute("user",user);
        model.addAttribute("clubs",clubs);
//        model.addAttribute("txt","hello from thymaleaf");
        System.out.println("hi");
        return "clubs-list";
    }

    //we will use this method to instantiate object from our request model (data model)
    //that we will use it to pass post request fildes into it to be able to get
    //form inputs to deal with it
    @GetMapping("/clubs/new")
    public String createClubForm(Model model){
        Club club = new Club();
        model.addAttribute("club",club);
        return "clubs-create";
    }

    @PostMapping("/clubs/new")
    public String saveClub(@Valid @ModelAttribute("club") ClubDto clubDto, BindingResult result, Model model){
        if(result.hasErrors()) {
            model.addAttribute("club", clubDto);
//            if (result.getFieldError("title") != null) {
//                model.addAttribute("titleMessage", result.getFieldError("title").getDefaultMessage());
//            }
//            if (result.getFieldError("photoUrl") != null) {
//                model.addAttribute("photoUrlMessage", result.getFieldError("photoUrl").getDefaultMessage());
//            }
//            if (result.getFieldError("content") != null) {
//                model.addAttribute("contentMessage", result.getFieldError("content").getDefaultMessage());
//            }
            return "clubs-create";
        }
        clubService.saveClub(clubDto);
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/{clubId}/edit")
    public String editClubForm(@PathVariable("clubId") Integer clubId,Model model){
        ClubDto club = clubService.findClubById(clubId);
        model.addAttribute("club",club);
        return "clubs-edit";
    }

    @PostMapping("/clubs/{clubId}/edit")
    public String updateClub(@PathVariable("clubId") Integer clubId,
                             @Valid @ModelAttribute("club") ClubDto clubdto,
                             BindingResult result,
                             Model model){
        if(result.hasErrors()){
            model.addAttribute("club",clubdto);
            if(result.getFieldError("title")!=null){
                model.addAttribute("titleMessage",result.getFieldError("title").getDefaultMessage());
            }
            if(result.getFieldError("photoUrl")!=null){
                model.addAttribute("photoUrlMessage",result.getFieldError("photoUrl").getDefaultMessage());
            }
            if(result.getFieldError("content")!=null){
                model.addAttribute("contentMessage",result.getFieldError("content").getDefaultMessage());
            }

            return "clubs-edit";
        }
        clubService.updateClub(clubdto);
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/{clubId}")
    public String clubDetails(@PathVariable("clubId") Integer clubId, Model model){
        UserEntity user = new UserEntity();
        ClubDto clubDto = clubService.findClubById(clubId);
        String username = SecurityUtil.getSessionUser();
        if (username != null){
            user = userService.findByUsername(username);
            model.addAttribute("user",user);
        }
        model.addAttribute("user",user);
        model.addAttribute("club",clubDto);
        return "clubs-detail";
    }

    @GetMapping("/clubs/{clubId}/delete")
    public String deleteClub(@PathVariable("clubId") Integer clubId){
        clubService.delete(clubId);
        return "redirect:/clubs";

    }

    @GetMapping("clubs/search")
    public String searchClubs(@RequestParam(name = "query") String query, Model model){
        UserEntity user = new UserEntity();
        List<ClubDto> clubs =clubService.serachClubs(query);
        String username = SecurityUtil.getSessionUser();
        if (username != null){
            user = userService.findByUsername(username);
//            model.addAttribute("user",user);
        }
        model.addAttribute("user",user);
        model.addAttribute("clubs",clubs);
        return "clubs-list";
    }

//    @GetMapping("test/batch")
//    public String insertBatch(){
//        batchEntity batch = new batchEntity();
//        batch.setBatch_id(1);
//        batch.setFile_name("test");
//        batch.setBatch_size(2000);
//        batch.setIp("10.11.214.44");
//        batch.setIgnored("anything");
//        batchRepository.save(batch);
//        return "clubs-list";
//
//    }


}
