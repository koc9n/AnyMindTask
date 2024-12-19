DO
$$
    DECLARE
        startDateTime TIMESTAMP;
        endDateTime
                      TIMESTAMP;
        intervalMinutes
                      INT;
        currentDateTime
                      TIMESTAMP;
        paymentMethod
                      VARCHAR(255);
        additionalItem
                      JSONB;
        price
                      DOUBLE PRECISION;
        priceModifier
                      DOUBLE PRECISION;
        finalPrice
                      DOUBLE PRECISION;
        points
                      DOUBLE PRECISION;
    BEGIN
        startDateTime
            := NOW() - INTERVAL '2 days';
        endDateTime
            := NOW();
        intervalMinutes
            := 20;
        currentDateTime
            := startDateTime;
        WHILE
            currentDateTime <= endDateTime
            LOOP
                price := RANDOM() * 100;
                paymentMethod
                    := CASE FLOOR(RANDOM() * 12)
                           WHEN 0 THEN 'CASH'
                           WHEN 1 THEN 'CASH_ON_DELIVERY'
                           WHEN 2 THEN 'VISA'
                           WHEN 3 THEN 'MASTERCARD'
                           WHEN 4 THEN 'AMEX'
                           WHEN 5 THEN 'JCB'
                           WHEN 6 THEN 'LINE_PAY'
                           WHEN 7 THEN 'PAYPAY'
                           WHEN 8 THEN 'POINTS'
                           WHEN 9 THEN 'GRAB_PAY'
                           WHEN 11 THEN 'BANK_TRANSFER'
                           ELSE 'CHEQUE'
                    END;

                CASE paymentMethod
                    WHEN 'CASH' THEN priceModifier := 0.9 + RANDOM() * 0.1;
                                     points
                                         := price * 0.05;
                                     additionalItem
                                         := '{}'::jsonb;
                    WHEN 'CASH_ON_DELIVERY' THEN priceModifier := 1.0 + RANDOM() * 0.02;
                                                 points
                                                     := price * 0.05;
                                                 additionalItem
                                                     := jsonb_build_object('courier', CASE FLOOR(RANDOM() * 2)
                                                                                          WHEN 0 THEN 'YAMATO'
                                                                                          ELSE 'SAGAWA' END);
                    WHEN 'VISA', 'MASTERCARD', 'AMEX', 'JCB' THEN priceModifier := 0.95 + RANDOM() * 0.05;
                                                                  points
                                                                      := CASE paymentMethod
                                                                             WHEN 'AMEX' THEN price * 0.02
                                                                             ELSE price * 0.03
                                                                      END;
                                                                  additionalItem
                                                                      := jsonb_build_object('last4',
                                                                                            LPAD(FLOOR(RANDOM() * 10000)::text, 4, '0'));
                    WHEN 'LINE_PAY', 'PAYPAY', 'GRAB_PAY' THEN priceModifier := 1.0;
                                                               points
                                                                   := price * 0.01;
                                                               additionalItem
                                                                   := '{}'::jsonb;
                    WHEN 'POINTS' THEN priceModifier := 1.0;
                                       points
                                           := 0;
                                       additionalItem
                                           := '{}'::jsonb;
                    WHEN 'BANK_TRANSFER' THEN priceModifier := 1.0;
                                              points
                                                  := 0;
                                              additionalItem
                                                  := jsonb_build_object('bankName', 'BankName', 'accountNumber',
                                                                        LPAD(FLOOR(RANDOM() * 1000000000)::text, 10, '0'));
                    ELSE priceModifier := 0.9 + RANDOM() * 0.1;
                         points
                             := 0;
                         additionalItem
                             := jsonb_build_object('bankName', 'BankName', 'chequeNumber',
                                                   LPAD(FLOOR(RANDOM() * 1000000000)::text, 10, '0'));
                    END CASE;

                finalPrice
                    := price * priceModifier;

                INSERT INTO payment (customer_id, price, price_modifier, payment_method, datetime, additional_item,
                                     final_price, points)
                VALUES (LPAD(FLOOR(RANDOM() * 1000000)::text, 6, '0'), price, priceModifier, paymentMethod,
                        currentDateTime, additionalItem, finalPrice, points);

                currentDateTime
                    := currentDateTime + INTERVAL '1 minute' * intervalMinutes;
            END LOOP;
    END
$$;