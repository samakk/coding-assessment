package com.krisksama.codingassessment.service;

import java.util.ArrayList;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.krisksama.codingassessment.model.AlgoData;
import com.krisksama.codingassessment.model.AlgoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * This is your team’s code and should be changed as you see fit.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class Application implements SignalHandler {

    private final AlgoRepository algoRepository;

    public void handleSignal(int signal) {

        // find corresponding signal for id from db
        Optional<AlgoData> data = algoRepository.findById(signal);

        Map<String, ArrayList<Integer>> sequence = null;

        try {
            // signal sequence is stored as json
            // deserialize as Map<String, ArrayList<Integer>>
            sequence = new ObjectMapper().readValue(data.get().getSequence(), new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            // implement exception handling at rest boundary ...
            throw new RuntimeException(e);
        } catch (NoSuchElementException e) {
            // default behaviour
            Algo algo = new Algo();
            algo.cancelTrades();
            algo.doAlgo();
        }

        if (sequence != null) {
            Algo algo = new Algo();
            processSequence(sequence, algo);
            algo.doAlgo();
        }

    }

    private void processSequence(Map<String, ArrayList<Integer>> sequence, Algo algo) {
        for (Map.Entry<String, ArrayList<Integer>> entry : sequence.entrySet()) {
            switch (entry.getKey()) {
                case "cancelTrades" -> algo.cancelTrades();
                case "reverse" -> algo.reverse();
                case "submitToMarket" -> algo.submitToMarket();
                case "performCalc" -> algo.performCalc();
                case "setUp" -> algo.setUp();
                case "setAlgoParam" -> {
                    ArrayList<Integer> value = entry.getValue();
                    algo.setAlgoParam(value.get(0), value.get(1));
                }
                default -> log.warn("Method not found!");
            }
        }
    }
}
