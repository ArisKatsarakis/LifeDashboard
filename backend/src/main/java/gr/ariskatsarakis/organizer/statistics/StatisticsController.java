package gr.ariskatsarakis.organizer.statistics;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.ariskatsarakis.organizer.statistics.StatisticsService.StatisticsMonth;

@RestController
@RequestMapping("/api/v1/stats")
public class StatisticsController {
        private StatisticsService statisticsService;

        public StatisticsController(
                        StatisticsService statisticsService) {
                this.statisticsService = statisticsService;
        }

        @GetMapping("/months/expenses")
        public List<StatisticsMonth> fetchExpenseMonts() {
                return statisticsService.fetchExpenseMoths();
        }

}
