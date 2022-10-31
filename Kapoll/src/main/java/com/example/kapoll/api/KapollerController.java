package com.example.kapoll.api;

import com.example.kapoll.Kapoll_db.PopulateDB;
import com.example.kapoll.Kapoll_db.daoImplementation.KapollerDAO;
import com.example.kapoll.api.dto.KapollerDTO;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KapollerController {

  private final KapollerDAO kapollerDAO = new KapollerDAO();

  @GetMapping("/Kapoller")
  List<KapollerDTO> getAllKapollers() {
    // Just save something so we get something in return.
    PopulateDB.main(new String[]{});

    return kapollerDAO.getAll().stream().map(KapollerDTO::map).toList();
  }
}
