package com.websocket.bia.webworker;

import org.springframework.stereotype.Service;

@Service
public class WebWorkerService {

    public void runInBackground(Runnable task) {
        new Thread(task).start();
    }
}
