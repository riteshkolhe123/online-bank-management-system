package org.dnyanyog.service;

import java.util.List;

import org.dnyanyog.dto.request.ChangePinRequest;
import org.dnyanyog.dto.response.ChangePinResponse;
import org.dnyanyog.entity.Account;
import org.dnyanyog.repo.AddAccountServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChangePinService {

    @Autowired
    AddAccountServiceRepository repo;

    @Autowired
    ChangePinResponse response;

    public ChangePinResponse changePin(ChangePinRequest changePin) {
        List<Account> accounts = repo.findByAtmPin(changePin.getCurrPin());

        if (!accounts.isEmpty()) {
            Account acc = accounts.get(0);

            if (acc.getAtmPin().equals(changePin.getCurrPin())) {
                // Update the PIN with the new PIN provided in the request
                acc.setAtmPin(changePin.getNewPin());
                repo.save(acc);

                response.setStatus("Success");
                response.setMessage("PIN Updated Successfully");
            } else {
                response.setStatus("Error");
                response.setMessage("Current PIN is incorrect");
            }
        } else {
            response.setStatus("Error");
            response.setMessage("Account not found");
        }
        return response;
    }
}
