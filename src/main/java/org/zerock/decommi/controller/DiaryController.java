// package org.zerock.decommi.controller;

// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.servlet.mvc.support.RedirectAttributes;
// import org.zerock.decommi.dto.DiaryDTO;
// import org.zerock.decommi.dto.PageRequestDTO;
// import org.zerock.decommi.service.diary.DiaryService;

// import lombok.RequiredArgsConstructor;
// import lombok.extern.log4j.Log4j2;

// @RestController
// @RequestMapping("/api/diary")
// @Log4j2
// @RequiredArgsConstructor
// public class DiaryController {

//     private final DiaryService diaryService;

//     @GetMapping("/register")
//     public void register() {
//     }

//     @PostMapping("/register")
//     public String registerPost(DiaryDTO dto, RedirectAttributes ra) {
//         log.info("register post" + dto);
//         Long dino = diaryService.register(dto);
//         ra.addFlashAttribute("msg", dino + "등록되었습니다.");
//         return "redirect:/diary/list";
//     }

//     @GetMapping({ "", "/" })
//     public String list() {
//         return "redirect:/diary/list";
//     }

//     @GetMapping("/list")
//     public void list(@ModelAttribute("requestDTO") PageRequestDTO req, Model model) {
//         log.info("PageRequest:" + req);
//         model.addAttribute("result", diaryService.getPageList(req));
//     }

//     @GetMapping({ "/read", "/modify" })
//     public void read(long dino,
//             @ModelAttribute("requestDTO") PageRequestDTO req, Model model) {
//         DiaryDTO dto = diaryService.getDiary(dino);
//         model.addAttribute("dto", dto);
//     }

//     @PostMapping("/modify")
//     public String modify(DiaryDTO dto, RedirectAttributes ra, PageRequestDTO req) {
//         diaryService.modify(dto);
//         ra.addFlashAttribute("msg", dto.getDino() + " 수정");
//         ra.addAttribute("dino", dto.getDino());
//         ra.addAttribute("page", req.getPage());
//         ra.addAttribute("type", req.getType());
//         ra.addAttribute("keyword", req.getKeyword());
//         return "redirect:/diary/read";
//     }
// }
