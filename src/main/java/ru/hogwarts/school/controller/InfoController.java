package ru.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.service.InfoService;

@RestController
@RequestMapping("/info")
@Tag(name = "Информация")
public class InfoController {
    private final InfoService infoService;

    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @Operation(
            tags = "Информация",
            summary = "Вывод корректного порта"
    )

    @GetMapping("/getPort")
    public String getCurrentPort() {
        return infoService.getCurrentPort();
    }

    @Operation(
            tags = "Информация",
            summary = "Вывод суммы"
    )

    @GetMapping("/getSum")
    public ResponseEntity<Integer> getSum() {
        return ResponseEntity.ok(infoService.getSum());
    }
}
