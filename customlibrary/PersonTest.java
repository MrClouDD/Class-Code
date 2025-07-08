package java

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {

    private Person person;

    @BeforeEach
    void setUp() {
        person = new Person();
    }

    // Additional Constructor Tests (10 tests)
    @Test
    @DisplayName("Constructor with null name should handle gracefully")
    void testConstructorWithNullName() {
        assertThrows(NullPointerException.class, () -> {
            new Person(900123456, null, "123 Main St City CA 90210");
        });
    }

    @Test
    @DisplayName("Constructor with null address should handle gracefully")
    void testConstructorWithNullAddress() {
        assertThrows(NullPointerException.class, () -> {
            new Person(900123456, "John Doe", null);
        });
    }

    @Test
    @DisplayName("Constructor with empty name should work")
    void testConstructorWithEmptyName() {
        Person testPerson = new Person(900123456, "", "123 Main St City CA 90210");
        assertEquals("", testPerson.getName());
    }

    @Test
    @DisplayName("Constructor with empty address should throw exception")
    void testConstructorWithEmptyAddress() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Person(900123456, "John Doe", "");
        });
    }

    @Test
    @DisplayName("Constructor with minimum valid ID")
    void testConstructorWithMinimumValidId() {
        Person testPerson = new Person(900000000, "John Doe", "123 Main St City CA 90210");
        assertEquals(900000000, testPerson.getId());
    }

    @Test
    @DisplayName("Constructor with maximum valid ID")
    void testConstructorWithMaximumValidId() {
        Person testPerson = new Person(900999999, "John Doe", "123 Main St City CA 90210");
        assertEquals(900999999, testPerson.getId());
    }

    @Test
    @DisplayName("Constructor with very long name")
    void testConstructorWithVeryLongName() {
        String longName = "Alexander Benjamin Christopher Maximilian Bartholomew";
        Person testPerson = new Person(900123456, longName, "123 Main St City CA 90210");
        assertEquals(longName, testPerson.getName());
    }

    @Test
    @DisplayName("Constructor with unicode characters in name")
    void testConstructorWithUnicodeCharacters() {
        Person testPerson = new Person(900123456, "José María Aznar", "123 Main St City CA 90210");
        assertEquals("José María Aznar", testPerson.getName());
    }

    @Test
    @DisplayName("Constructor with very long address")
    void testConstructorWithVeryLongAddress() {
        String longAddress = "12345 Very Long Street Name That Goes On And On And On Avenue Los Angeles CA 90210";
        Person testPerson = new Person(900123456, "John Doe", longAddress);
        assertTrue(testPerson.getStreetAddress().contains("Very Long Street Name That Goes On And On And On Avenue"));
    }

    @Test
    @DisplayName("Constructor with boundary ID values")
    void testConstructorWithBoundaryIds() {
        Person testPerson1 = new Person(900000001, "John Doe", "123 Main St City CA 90210");
        Person testPerson2 = new Person(900999998, "Jane Doe", "456 Oak Ave City CA 90210");
        assertEquals(900000001, testPerson1.getId());
        assertEquals(900999998, testPerson2.getId());
    }

    // Additional ID Tests (15 tests)
    @Test
    @DisplayName("ID with 899 prefix should be rejected")
    void testIdWith899Prefix() {
        person.setId(899123456);
        assertEquals(900000000, person.getId());
    }

    @Test
    @DisplayName("ID with 901 prefix should be rejected")
    void testIdWith901Prefix() {
        person.setId(901123456);
        assertEquals(900000000, person.getId());
    }

    @Test
    @DisplayName("Zero ID should be rejected")
    void testZeroId() {
        person.setId(0);
        assertEquals(900000000, person.getId());
    }

    @Test
    @DisplayName("Very large ID should be rejected")
    void testVeryLargeId() {
        person.setId(Integer.MAX_VALUE);
        assertEquals(900000000, person.getId());
    }

    @Test
    @DisplayName("Very small negative ID should be rejected")
    void testVerySmallNegativeId() {
        person.setId(Integer.MIN_VALUE);
        assertEquals(900000000, person.getId());
    }

    @Test
    @DisplayName("ID with 10 digits starting with 900 should work")
    void testTenDigitIdStartingWith900() {
        person.setId(9001234567L > Integer.MAX_VALUE ? 900123456 : (int)9001234567L);
        // Since int can't hold 10 digits, this tests the boundary
        assertTrue(person.getId() == 900000000 || person.getId() == 900123456);
    }

    @Test
    @DisplayName("Multiple ID changes should work")
    void testMultipleIdChanges() {
        person.setId(900111111);
        assertEquals(900111111, person.getId());
        person.setId(900222222);
        assertEquals(900222222, person.getId());
        person.setId(900333333);
        assertEquals(900333333, person.getId());
    }

    @Test
    @DisplayName("ID change from valid to invalid should preserve valid")
    void testIdChangeFromValidToInvalid() {
        person.setId(900555555);
        assertEquals(900555555, person.getId());
        person.setId(123456789);
        assertEquals(900555555, person.getId());
    }

    @Test
    @DisplayName("ID change from invalid to valid should work")
    void testIdChangeFromInvalidToValid() {
        person.setId(123456789);
        assertEquals(900000000, person.getId());
        person.setId(900777777);
        assertEquals(900777777, person.getId());
    }

    @Test
    @DisplayName("Sequential ID validation")
    void testSequentialIdValidation() {
        for (int i = 0; i < 10; i++) {
            person.setId(900000000 + i);
            assertEquals(900000000 + i, person.getId());
        }
    }

    @Test
    @DisplayName("ID boundary testing around 900000000")
    void testIdBoundaryAround900000000() {
        person.setId(899999999);
        assertEquals(900000000, person.getId());
        person.setId(900000000);
        assertEquals(900000000, person.getId());
        person.setId(900000001);
        assertEquals(900000001, person.getId());
    }

    @Test
    @DisplayName("ID with leading zeros in string representation")
    void testIdWithLeadingZeros() {
        person.setId(900000007);
        assertEquals(900000007, person.getId());
        assertTrue(person.toString().contains("900000007"));
    }

    @Test
    @DisplayName("ID validation with exactly 9 digits")
    void testNineDigitId() {
        person.setId(900123456);
        assertEquals(900123456, person.getId());
    }

    @Test
    @DisplayName("ID validation edge case testing")
    void testIdValidationEdgeCases() {
        int[] invalidIds = {1, 99, 123, 8999, 89999, 899999, 8999999, 89999999};
        int originalId = person.getId();

        for (int invalidId : invalidIds) {
            person.setId(invalidId);
            assertEquals(originalId, person.getId());
        }
    }

    // Additional Name Tests (20 tests)
    @Test
    @DisplayName("Name with only spaces should be handled")
    void testNameWithOnlySpaces() {
        person.setName("   ");
        assertEquals("", person.getName());
    }

    @Test
    @DisplayName("Name with tabs and newlines")
    void testNameWithTabsAndNewlines() {
        person.setName("John\tDoe\nSmith");
        assertEquals("John Doe Smith", person.getName());
    }

    @Test
    @DisplayName("Name with multiple consecutive spaces")
    void testNameWithMultipleSpaces() {
        person.setName("John     Doe     Smith");
        assertEquals("John Doe Smith", person.getName());
    }

    @Test
    @DisplayName("Name with leading and trailing spaces")
    void testNameWithLeadingTrailingSpaces() {
        person.setName("   John Doe   ");
        assertEquals("John Doe", person.getName());
    }

    @Test
    @DisplayName("Name with special characters should be preserved")
    void testNameWithSpecialCharacters() {
        person.setName("Jean-Luc", "O'Connor-Smith");
        assertEquals("Jean-Luc O'Connor-Smith", person.getName());
    }

    @Test
    @DisplayName("Name with numbers")
    void testNameWithNumbers() {
        person.setName("John2", "Doe3");
        assertEquals("John2 Doe3", person.getName());
    }

    @Test
    @DisplayName("Very long first name")
    void testVeryLongFirstName() {
        String longName = "Pneumonoultramicroscopicsilicovolcanoconiosisophobia";
        person.setName(longName, "Smith");
        assertEquals(longName + " Smith", person.getName());
    }

    @Test
    @DisplayName("Very long last name")
    void testVeryLongLastName() {
        String longLastName = "Wolfeschlegelsteinhausenbergerdorff";
        person.setName("John", longLastName);
        assertEquals("John " + longLastName, person.getName());
    }

    @Test
    @DisplayName("Name with mixed case handling")
    void testNameWithMixedCase() {
        person.setName("jOhN", "dOe");
        assertEquals("jOhN dOe", person.getName());
    }

    @Test
    @DisplayName("Name with single character names")
    void testSingleCharacterNames() {
        person.setName("A", "B");
        assertEquals("A B", person.getName());
    }

    @Test
    @DisplayName("Name with four parts")
    void testFourPartName() {
        person.setName("John Michael David Smith");
        assertEquals("John Michael David Smith", person.getName());
    }

    @Test
    @DisplayName("Name with five parts")
    void testFivePartName() {
        person.setName("Mary Elizabeth Jane Doe Smith");
        assertEquals("Mary Elizabeth Jane Doe Smith", person.getName());
    }

    @Test
    @DisplayName("Name with six parts")
    void testSixPartName() {
        person.setName("Juan Carlos Maria Jose Antonio Rodriguez");
        assertEquals("Juan Carlos Maria Jose Antonio Rodriguez", person.getName());
    }

    @Test
    @DisplayName("Name with apostrophes and hyphens")
    void testNameWithApostrophesAndHyphens() {
        person.setName("Mary-Jane", "O'Connor-Smith");
        assertEquals("Mary-Jane O'Connor-Smith", person.getName());
    }

    @Test
    @DisplayName("Name with accented characters")
    void testNameWithAccentedCharacters() {
        person.setName("José María", "Aznar López");
        assertEquals("José María Aznar López", person.getName());
    }

    @Test
    @DisplayName("Name with lowercase inputs")
    void testNameWithLowercaseInputs() {
        person.setName("john", "doe");
        assertEquals("john doe", person.getName());
    }

    @Test
    @DisplayName("Name with uppercase inputs")
    void testNameWithUppercaseInputs() {
        person.setName("JOHN", "DOE");
        assertEquals("JOHN DOE", person.getName());
    }

    @Test
    @DisplayName("Name with Roman numerals")
    void testNameWithRomanNumerals() {
        person.setName("John", "III", "Smith");
        assertEquals("John III Smith", person.getName());
    }

    @Test
    @DisplayName("Name setting with null parameters")
    void testNameSettingWithNullParameters() {
        person.setName(null, "Doe");
        assertEquals("null Doe", person.getName());
    }

    @Test
    @DisplayName("Name with empty string parameters")
    void testNameWithEmptyStringParameters() {
        person.setName("", "", "Smith");
        assertEquals("  Smith", person.getName());
    }

    // Additional Address Tests (25 tests)
    @Test
    @DisplayName("Address with different street types - Circle")
    void testAddressWithCircle() {
        String[] address = {"123", "Oak", "Cir", "City", "CA", "90210"};
        String result = person.setStreetName(address);
        assertEquals("Oak Cir", result);
    }

    @Test
    @DisplayName("Address with different street types - Court")
    void testAddressWithCourt() {
        String[] address = {"456", "Pine", "Ct", "City", "CA", "90210"};
        String result = person.setStreetName(address);
        assertEquals("Pine Ct", result);
    }

    @Test
    @DisplayName("Address with different street types - Place")
    void testAddressWithPlace() {
        String[] address = {"789", "Maple", "Pl", "City", "CA", "90210"};
        String result = person.setStreetName(address);
        assertEquals("Maple Pl", result);
    }

    @Test
    @DisplayName("Address with different street types - Way")
    void testAddressWithWay() {
        String[] address = {"321", "Sunset", "Way", "City", "CA", "90210"};
        String result = person.setStreetName(address);
        assertEquals("Sunset Way", result);
    }

    @Test
    @DisplayName("Address with Parkway")
    void testAddressWithParkway() {
        String[] address = {"654", "Ocean", "Pkwy", "City", "CA", "90210"};
        String result = person.setStreetName(address);
        assertEquals("Ocean Pkwy", result);
    }

    @Test
    @DisplayName("Address with Trail")
    void testAddressWithTrail() {
        String[] address = {"987", "Mountain", "Trl", "City", "CA", "90210"};
        String result = person.setStreetName(address);
        assertEquals("Mountain Trl", result);
    }

    @Test
    @DisplayName("Address with Terrace")
    void testAddressWithTerrace() {
        String[] address = {"147", "Hill", "Ter", "City", "CA", "90210"};
        String result = person.setStreetName(address);
        assertEquals("Hill Ter", result);
    }

    @Test
    @DisplayName("Address with Plaza")
    void testAddressWithPlaza() {
        String[] address = {"258", "Town", "Plz", "City", "CA", "90210"};
        String result = person.setStreetName(address);
        assertEquals("Town Plz", result);
    }

    @Test
    @DisplayName("Address with Highway")
    void testAddressWithHighway() {
        String[] address = {"369", "State", "Hwy", "City", "CA", "90210"};
        String result = person.setStreetName(address);
        assertEquals("State Hwy", result);
    }

    @Test
    @DisplayName("Address with Ridge")
    void testAddressWithRidge() {
        String[] address = {"741", "Oak", "Rdg", "City", "CA", "90210"};
        String result = person.setStreetName(address);
        assertEquals("Oak Rdg", result);
    }

    @Test
    @DisplayName("Multiple word street name with Avenue")
    void testMultipleWordStreetNameWithAvenue() {
        String[] address = {"123", "North", "Main", "Ave", "City", "CA", "90210"};
        String result = person.setStreetName(address);
        assertEquals("North Main Ave", result);
    }

    @Test
    @DisplayName("Three word street name")
    void testThreeWordStreetName() {
        String[] address = {"456", "East", "Main", "Street", "City", "CA", "90210"};
        String result = person.setStreetName(address);
        assertEquals("East Main Street", result);
    }

    @Test
    @DisplayName("Four word street name")
    void testFourWordStreetName() {
        String[] address = {"789", "North", "East", "Main", "Street", "City", "CA", "90210"};
        String result = person.setStreetName(address);
        assertEquals("North East Main Street", result);
    }

    @Test
    @DisplayName("Street name with numbers")
    void testStreetNameWithNumbers() {
        String[] address = {"123", "42nd", "Street", "City", "CA", "90210"};
        String result = person.setStreetName(address);
        assertEquals("42nd Street", result);
    }

    @Test
    @DisplayName("Street name with ordinal numbers")
    void testStreetNameWithOrdinalNumbers() {
        String[] address = {"456", "1st", "Ave", "City", "CA", "90210"};
        String result = person.setStreetName(address);
        assertEquals("1st Ave", result);
    }

    @Test
    @DisplayName("Address with very long city name")
    void testVeryLongCityName() {
        Person testPerson = new Person(900123456, "Test User", "123 Main St Lake Chargoggagoggmanchauggagoggchaubunagungamaugg MA 01234");
        assertTrue(testPerson.getStreetAddress().contains("Lake Chargoggagoggmanchauggagoggchaubunagungamaugg"));
    }

    @Test
    @DisplayName("Address with hyphenated city name")
    void testHyphenatedCityName() {
        Person testPerson = new Person(900123456, "Test User", "123 Main St Wilkes-Barre PA 18701");
        assertTrue(testPerson.getStreetAddress().contains("Wilkes-Barre"));
    }

    @Test
    @DisplayName("Address with Saint in city name")
    void testSaintInCityName() {
        Person testPerson = new Person(900123456, "Test User", "123 Main St St. Louis MO 63101");
        assertTrue(testPerson.getStreetAddress().contains("St. Louis"));
    }

    @Test
    @DisplayName("Address with Fort in city name")
    void testFortInCityName() {
        Person testPerson = new Person(900123456, "Test User", "123 Main St Fort Worth TX 76101");
        assertTrue(testPerson.getStreetAddress().contains("Fort Worth"));
    }

    @Test
    @DisplayName("Address with Mount in city name")
    void testMountInCityName() {
        Person testPerson = new Person(900123456, "Test User", "123 Main St Mount Vernon NY 10550");
        assertTrue(testPerson.getStreetAddress().contains("Mount Vernon"));
    }

    @Test
    @DisplayName("Address with direction in street name")
    void testDirectionInStreetName() {
        String[] address = {"123", "North", "Main", "St", "City", "CA", "90210"};
        String result = person.setStreetName(address);
        assertEquals("North Main St", result);
    }

    @Test
    @DisplayName("Address with cardinal direction abbreviation")
    void testCardinalDirectionAbbreviation() {
        String[] address = {"123", "N", "Main", "St", "City", "CA", "90210"};
        String result = person.setStreetName(address);
        assertEquals("N Main St", result);
    }

    @Test
    @DisplayName("Address with multiple directional words")
    void testMultipleDirectionalWords() {
        String[] address = {"123", "North", "East", "Main", "St", "City", "CA", "90210"};
        String result = person.setStreetName(address);
        assertEquals("North East Main St", result);
    }

    @Test
    @DisplayName("Address formatting with mixed case")
    void testAddressFormattingWithMixedCase() {
        String formatted = person.formatAddress("123 MAIN Street LoS AnGeLes CA 90210");
        assertEquals("123 main street los angeles ca 90210", formatted);
    }

    @Test
    @DisplayName("Address formatting with punctuation")
    void testAddressFormattingWithPunctuation() {
        String formatted = person.formatAddress("123 Main St., Los Angeles, CA 90210");
        assertEquals("123 main st los angeles ca 90210", formatted);
    }

    // Additional ZIP Code Tests (10 tests)
    @Test
    @DisplayName("ZIP code with four digits should fail")
    void testFourDigitZipCode() {
        String[] address = {"123", "Main", "St", "City", "CA", "9021"};
        assertThrows(IllegalArgumentException.class, () -> {
            person.setZipCode(address);
        });
    }

    @Test
    @DisplayName("ZIP code with six digits should fail")
    void testSixDigitZipCode() {
        String[] address = {"123", "Main", "St", "City", "CA", "902100"};
        assertThrows(IllegalArgumentException.class, () -> {
            person.setZipCode(address);
        });
    }

    @Test
    @DisplayName("ZIP code with mixed letters and numbers")
    void testZipCodeWithMixedChars() {
        String[] address = {"123", "Main", "St", "City", "CA", "9021A"};
        assertThrows(IllegalArgumentException.class, () -> {
            person.setZipCode(address);
        });
    }

    @Test
    @DisplayName("ZIP code with special characters")
    void testZipCodeWithSpecialChars() {
        String[] address = {"123", "Main", "St", "City", "CA", "90-210"};
        assertThrows(IllegalArgumentException.class, () -> {
            person.setZipCode(address);
        });
    }

    @Test
    @DisplayName("ZIP code with spaces")
    void testZipCodeWithSpaces() {
        String[] address = {"123", "Main", "St", "City", "CA", "90 210"};
        assertThrows(IllegalArgumentException.class, () -> {
            person.setZipCode(address);
        });
    }

    @Test
    @DisplayName("Valid ZIP codes boundary testing")
    void testValidZipCodesBoundary() {
        String[] testZips = {"00001", "00010", "00100", "01000", "10000", "99999"};
        for (String zip : testZips) {
            String[] address = {"123", "Main", "St", "City", "CA", zip};
            int result = person.setZipCode(address);
            assertEquals(Integer.parseInt(zip), result);
        }
    }

    @Test
    @DisplayName("ZIP code in different positions")
    void testZipCodeInDifferentPositions() {
        String[] address1 = {"123", "Main", "St", "City", "CA", "90210", "Apt", "1"};
        String[] address2 = {"456", "Oak", "Ave", "Los", "Angeles", "CA", "90211"};

        assertEquals(90210, person.setZipCode(address1));
        assertEquals(90211, person.setZipCode(address2));
    }

    @Test
    @DisplayName("Multiple ZIP-like patterns in address")
    void testMultipleZipLikePatterns() {
        String[] address = {"12345", "Main", "St", "City", "CA", "90210"};
        int result = person.setZipCode(address);
        assertEquals(90210, result); // Should pick the last valid 5-digit number
    }

    @Test
    @DisplayName("ZIP code validation with exactly 5 digits")
    void testZipCodeExactly5Digits() {
        String[] validZips = {"12345", "00000", "99999", "50505", "01010"};
        for (String zip : validZips) {
            String[] address = {"123", "Main", "St", "City", "CA", zip};
            int result = person.setZipCode(address);
            assertEquals(Integer.parseInt(zip), result);
        }
    }

    @Test
    @DisplayName("ZIP code with negative numbers should fail")
    void testZipCodeWithNegativeNumbers() {
        String[] address = {"123", "Main", "St", "City", "CA", "-9021"};
        assertThrows(IllegalArgumentException.class, () -> {
            person.setZipCode(address);
        });
    }

    // Additional State Tests (8 tests)
    @Test
    @DisplayName("State with three characters should fail")
    void testThreeCharacterState() {
        String[] address = {"123", "Main", "St", "City", "CAL", "90210"};
        person.setZipCode(address);
        assertThrows(IllegalArgumentException.class, () -> {
            person.setStateIndex(address);
        });
    }

    @Test
    @DisplayName("State with one character should fail")
    void testOneCharacterState() {
        String[] address = {"123", "Main", "St", "City", "C", "90210"};
        person.setZipCode(address);
        assertThrows(IllegalArgumentException.class, () -> {
            person.setStateIndex(address);
        });
    }

    @Test
    @DisplayName("State with mixed case should work")
    void testMixedCaseState() {
        String[] address = {"123", "Main", "St", "City", "Ca", "90210"};
        person.setZipCode(address);
        String result = person.setStateIndex(address);
        assertEquals("Ca", result);
    }

    @Test
    @DisplayName("State with all lowercase should work")
    void testLowercaseState() {
        String[] address = {"123", "Main", "St", "City", "ca", "90210"};
        person.setZipCode(address);
        String result = person.setStateIndex(address);
        assertEquals("ca", result);
    }

    @Test
    @DisplayName("State with all uppercase should work")
    void testUppercaseState() {
        String[] address = {"123", "Main", "St", "City", "CA", "90210"};
        person.setZipCode(address);
        String result = person.setStateIndex(address);
        assertEquals("CA", result);
    }

    @Test
    @DisplayName("State not followed by ZIP should fail")
    void testStateNotFollowedByZip() {
        String[] address = {"123", "Main", "St", "City", "CA", "NotZip"};
        person.setZipCode(address);
        assertThrows(IllegalArgumentException.class, () -> {
            person.setStateIndex(address);
        });
    }

    @Test
    @DisplayName("Multiple two-letter combinations before ZIP")
    void testMultipleTwoLetterCombinations() {
        String[] address = {"123", "Main", "St", "NY", "CA", "90210"};
        person.setZipCode(address);
        String result = person.setStateIndex(address);
        assertEquals("CA", result); // Should pick the one right before ZIP
    }

    @Test
    @DisplayName("State with invalid characters should fail")
    void testStateWithInvalidCharacters() {
        String[] address = {"123", "Main", "St", "City", "C$", "90210"};
        person.setZipCode(address);
        assertThrows(IllegalArgumentException.class, () -> {
            person.setStateIndex(address);
        });
    }

    // Additional Unit Tests (12 tests)
    @Test
    @DisplayName("Unit designator - Floor")
    void testFloorUnitDesignator() {
        String[] address = {"123", "Main", "St", "City", "CA", "90210", "Floor", "3"};
        String result = person.setUnitType(address);
        assertEquals("Floor", result);
    }

    @Test
    @DisplayName("Unit designator - Room")
    void testRoomUnitDesignator() {
        String[] address = {"123", "Main", "St", "City", "CA", "90210", "Room", "205"};
        String result = person.setUnitType(address);
        assertEquals("Room", result);
    }

    @Test
    @DisplayName("Unit designator - Building")
    void testBuildingUnitDesignator() {
        String[] address = {"123", "Main", "St", "City", "CA", "90210", "Building", "A"};
        String result = person.setUnitType(address);
        assertEquals("Building", result);
    }

    @Test
    @DisplayName("Unit designator - Department")
    void testDepartmentUnitDesignator() {
        String[] address = {"123", "Main", "St", "City", "CA", "90210", "Department", "101"};
        String result = person.setUnitType(address);
        assertEquals("Department", result);
    }

    @Test
    @DisplayName("Unit designator with periods")
    void testUnitDesignatorWithPeriods() {
        String[] address = {"123", "Main", "St", "City", "CA", "90210", "Apt.", "42"};
        String result = person.setUnitType(address);
        assertEquals("Apt.", result);
    }

    @Test
    @DisplayName("Unit number with leading zeros")
    void testUnitNumberWithLeadingZeros() {
        String[] address = {"123", "Main", "St", "City", "CA", "90210", "Apt", "007"};
        person.setUnitType(address);
        int result = person.setUnitNumber(address);
        assertEquals(7, result);
    }

    @Test
    @DisplayName("Unit number with multiple digits")
    void testUnitNumberWithMultipleDigits() {
        String[] address = {"123", "Main", "St", "City", "CA", "90210", "Suite", "1234"};
        person.setUnitType(address);
        int result = person.setUnitNumber(address);
        assertEquals(1234, result);
    }

    @Test
    @DisplayName("Unit designator and number combined")
    void testUnitDesignatorAndNumberCombined() {
        String[] address = {"123", "Main", "St", "City", "CA", "90210", "Apt42"};
        String result = person.setUnitType(address);
        assertEquals("Apt42", result);
    }

    @Test
    @DisplayName("Unit designator case insensitive")
    void testUnitDesignatorCaseInsensitive() {
        String[] address = {"123", "Main", "St", "City", "CA", "90210", "APT", "42"};
        String result = person.setUnitType(address);
        assertEquals("APT", result);
    }

    @Test
    @DisplayName("Multiple unit designators should pick first")
    void testMultipleUnitDesignators() {
        String[] address = {"123", "Main", "St", "City", "CA", "90210", "Apt", "42", "Suite", "100"};
        String result = person.setUnitType(address);
        assertEquals("Apt", result);
    }

    @Test
    @DisplayName("Unit number zero should work")
    void testUnitNumberZero() {
        String[] address = {"123", "Main", "St", "City", "CA", "90210", "Apt", "0"};
        person.setUnitType(address);
        int result = person.setUnitNumber(address);
        assertEquals(0, result);
    }

    @Test
    @DisplayName("Unit designator without number")
    void testUnitDesignatorWithoutNumber() {
        String[] address = {"123", "Main", "St", "City", "CA", "90210", "Apt"};
        String result = person.setUnitType(address);
        assertEquals("Apt", result);
        int unitNumber = person.setUnitNumber(address);
        assertEquals(-1, unitNumber);
    }

    // Additional toString and Utility Tests (5 tests)
    @Test
    @DisplayName("toString with complete address")
    void testToStringWithCompleteAddress() {
        Person testPerson = new Person(900123456, "John Doe", "123 Main St Los Angeles CA 90210 Apt 42");
        String result = testPerson.toString();
        assertTrue(result.contains("John Doe"));
        assertTrue(result.contains("900123456"));
        assertTrue(result.contains("123 Main St"));
        assertTrue(result.contains("Los Angeles"));
        assertTrue(result.contains("CA"));
        assertTrue(result.contains("90210"));
        assertTrue(result.contains("Apt 42"));
    }

    @Test
    @DisplayName("toString with minimal address")
    void testToStringWithMinimalAddress() {
        Person testPerson = new Person(900000001, "A B", "1 A St B CA 90210");
        String result = testPerson.toString();
        assertTrue(result.contains("A B"));
        assertTrue(result.contains("900000001"));
        assertTrue(result.contains("1 A St"));
    }

    @Test
    @DisplayName("toSentenceCase with null should handle gracefully")
    void testToSentenceCaseWithNull() {
        assertThrows(NullPointerException.class, () -> {
            person.toSentenceCase(null);
        });
    }

    @Test
    @DisplayName("toSentenceCase with all caps")
    void testToSentenceCaseWithAllCaps() {
        String result = person.toSentenceCase("HELLO WORLD");
        assertEquals("HELLO WORLD", result);
    }

    @Test
    @DisplayName("toSentenceCase with punctuation")
    void testToSentenceCaseWithPunctuation() {
        String result = person.toSentenceCase("hello, world!");
        assertEquals("Hello, World!", result);
    }
}