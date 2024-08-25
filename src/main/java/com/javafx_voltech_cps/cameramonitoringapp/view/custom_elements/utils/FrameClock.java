package com.javafx_voltech_cps.cameramonitoringapp.view.custom_elements.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class FrameClock {
    private ScheduledExecutorService scheduler;
    private ScheduledFuture<?> futureTask;
    private volatile boolean isRunning = false;
    private int fps;

    public FrameClock(int fps) {
        this.fps = fps;
        scheduler = Executors.newSingleThreadScheduledExecutor();
    }

    public void start() {
        if (!isRunning) {
            int interval = 1000 / fps; // Calcula o intervalo em milissegundos
            futureTask = scheduler.scheduleAtFixedRate(this::run, 0, interval, TimeUnit.MILLISECONDS);
            isRunning = true;
        }
    }

    public void pause() {
        if (isRunning && futureTask != null) {
            futureTask.cancel(false); // Cancela a tarefa atual, mas não interrompe o thread
            isRunning = false;
        }
    }

    public void shutdown() {
        scheduler.shutdown(); // Inicia o processo de encerramento
        try {
            if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                scheduler.shutdownNow(); // Força a interrupção das tarefas ainda ativas
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow(); // Força a interrupção em caso de interrupção da thread
            Thread.currentThread().interrupt(); // Restaura o status de interrupção
        }
    }

    public synchronized void setFps(int newFps) {
        if (fps != newFps) {
            // Pausa o FrameClock atual
            pause();
            // Atualiza o fps
            this.fps = newFps;
            // Reinicia o FrameClock com o novo fps
            start();
        }
    }

    public void run() {
    }
}
