package cl.antol.conversor.principal;

import java.util.Map;

public record GeneradorDeArchivo(
        String result,
        String base_code,
        long time_last_update_unix,
        String time_last_update_utc,
        long time_next_update_unix,
        String time_next_update_utc,
        Map<String, Double> conversion_rates
) {

    @Override
    public String toString() {
        return "GeneradorDeArchivo{" +
                "result='" + result + '\'' +
                ", base_code='" + base_code + '\'' +
                ", time_last_update_unix=" + time_last_update_unix +
                ", time_last_update_utc='" + time_last_update_utc + '\'' +
                ", time_next_update_unix=" + time_next_update_unix +
                ", time_next_update_utc='" + time_next_update_utc + '\'' +
                ", conversion_rates=" + conversion_rates +
                '}';
    }
}

