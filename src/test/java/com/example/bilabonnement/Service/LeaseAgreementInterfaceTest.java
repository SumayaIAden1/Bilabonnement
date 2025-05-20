package com.example.bilabonnement.Service;

import com.example.bilabonnement.Service.Interface.FakeLeaseAgreementService;
import com.example.bilabonnement.Service.Interface.LeaseAgreementServiceInterface;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

//Isabella
public class LeaseAgreementInterfaceTest
{

    @Test
    void shouldReturnExpectedDataFromFakeService()
    {
        // Arrange – brug fake service i stedet for rigtig
        LeaseAgreementServiceInterface service = new FakeLeaseAgreementService();

        // Act – kald metoder fra faken
        int activeLeases = service.getActiveLeaseCount();
        double totalPrice = service.getTotaltPriceOfLeasedCars();

        // Assert – forvent præcis det vi har defineret i fake
        assertEquals(2, activeLeases); // Fake returnerer 2
        assertEquals(56789.0, totalPrice); // Fake returnerer 56789.0
    }
}
