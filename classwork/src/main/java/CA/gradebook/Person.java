package CA.gradebook;

import java.util.Arrays;

/**
 * Core Person class for representing an individual with name and address.
 * Handles parsing and formatting of address components (street, city, state, zip, unit).
 * Includes validation for names and secure handling of address data.
 * 
 * @author Ajitesh Sandhu
 */

public class Person {
    private String fName, mName, lName, streetName, city, state, unitType, houseModifier;;
    private int id, houseNumber, zipCode, unitNumber;
    private int streetTypeIndex = -1, stateIndex = -1, zipIndex = -1, designatorIndex = -1;

    final String[] STREET_TYPES = {"ALLEY", "ALY", "ANNEX", "ANX", "ARCADE", "ARC", "AVENUE", "AVE", "BAYOU", "BYU", "BEACH", "BCH", "BEND", "BND", "BLUFF", "BLF", "BOTTOM", "BTM", "BOULEVARD", "BLVD", "BRANCH", "BR", "BRIDGE", "BRG", "BROOK", "BRK", "BURG", "BG", "BYPASS", "BYP", "CAMP", "CP", "CANYON", "CYN", "CAPE", "CPE", "CAUSEWAY", "CSWY", "CENTER", "CTR", "CIRCLE", "CIR", "CLIFF", "CLF", "CLUB", "CLB", "COMMON", "CMN", "CORNER", "COR", "COURSE", "CRSE", "COURT", "CT", "COVE", "CV", "CREEK", "CRK", "CRESCENT", "CRES", "CREST", "CRST", "CROSSING", "XING", "DALE", "DL", "DAM", "DM", "DIVIDE", "DV", "DRIVE", "DR", "ESTATE", "EST", "EXPRESSWAY", "EXPY", "EXTENSION", "EXT", "FALLS", "FLS", "FERRY", "FRY", "FIELD", "FLD", "FLAT", "FLT", "FORD", "FRD", "FOREST", "FRST", "FORGE", "FRG", "FORK", "FRK", "FORT", "FT", "FREEWAY", "FWY", "GARDEN", "GDN", "GATEWAY", "GTWY", "GLEN", "GLN", "GREEN", "GRN", "GROVE", "GRV", "HARBOR", "HBR", "HAVEN", "HVN", "HEIGHTS", "HTS", "HIGHWAY", "HWY", "HILL", "HL", "HOLLOW", "HOLW", "INLET", "INLT", "ISLAND", "IS", "ISLE", "ISLE", "JUNCTION", "JCT", "KEY", "KY", "KNOLL", "KNL", "LAKE", "LK", "LAND", "LAND", "LANDING", "LNDG", "LANE", "LN", "LIGHT", "LGT", "LOAF", "LF", "LOCK", "LCK", "LODGE", "LDG", "LOOP", "LOOP", "MALL", "MALL", "MANOR", "MNR", "MEADOW", "MDW", "MEWS", "MEWS", "MILL", "ML", "MISSION", "MSN", "MOTORWAY", "MTWY", "MOUNT", "MT", "MOUNTAIN", "MTN", "NECK", "NCK", "ORCHARD", "ORCH", "OVAL", "OVAL", "PARK", "PARK", "PARKWAY", "PKWY", "PASS", "PASS", "PATH", "PATH", "PIKE", "PIKE", "PINE", "PNE", "PLACE", "PL", "PLAIN", "PLN", "PLAZA", "PLZ", "POINT", "PT", "PORT", "PRT", "PRAIRIE", "PR", "RADIAL", "RADL", "RANCH", "RNCH", "RAPID", "RPD", "REST", "RST", "RIDGE", "RDG", "RIVER", "RIV", "ROAD", "RD", "ROW", "ROW", "RUN", "RUN", "SHORE", "SHR", "SPRING", "SPG", "SPUR", "SPUR", "SQUARE", "SQ", "STATION", "STA", "STRAVENUE", "STRA", "STREAM", "STRM", "STREET", "ST", "SUMMIT", "SMT", "TERRACE", "TER", "TRACE", "TRCE", "TRACK", "TRAK", "TRAFFICWAY", "TRFY", "TRAIL", "TRL", "TUNNEL", "TUNL", "TURNPIKE", "TPKE", "UNDERPASS", "UPAS", "UNION", "UN", "VALLEY", "VLY", "VIADUCT", "VIA", "VIEW", "VW", "VILLAGE", "VLG", "VILLE", "VL", "VISTA", "VIS", "WALK", "WALK", "WALL", "WALL", "WAY", "WAY", "WELL", "WL", "WOOD", "WD", "WOODS", "WDS"};
    final String[] UNIT_DESIGNATORS = {"Apartment", "Apt.", "Apt", "Suite", "Ste.", "Ste", "Unit", "Building", "Bldg.", "Bldg", "Floor", "Fl.", "Fl", "Room", "Rm.", "Rm", "Department", "Dept.", "Dept", "Number", "No.", "No", "#", "Lot", "Trailer", "Trlr.", "Trlr", "Pier", "#"};

    public Person(String fname, String lname) {
        this.setName(fname, lname);
    }

    public Person(String fname, String lName, String address){
        this.setName(fname, lName);
        this.setStreetAddress(address);
    }


    Person() {
        this.setId(900000000);
        this.setName("John", "Doe");
        this.setStreetAddress("123 Main Street City ST 99999, Apt 232");
    }

    /**
     * Returns the full name of the person, including middle name if present.
     */
    public final String getName() {
        String fullName;
        if (mName != null){
            fullName = fName + " " + mName + " " + lName;
        } else if (lName == null) {
            fullName = fName;
        } else {
            fullName = fName + " " + lName;
        }
        return fullName;
    }

    /**
     * Sets first and last name after validation.
     */
    public final void setName(String fName, String lName) {
        if (fName == null || fName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be null or empty");
        }
        if (lName == null || lName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be null or empty");
        }
        this.fName = fName;
        this.mName = null;
        this.lName = lName;
    }

    /**
     * Sets first, middle, and last names after validation.
     */
    public final void setName(String fName, String mName, String lName) {
        if (fName == null || fName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be null or empty");
        }
        if (mName == null || mName.trim().isEmpty()) {
            throw new IllegalArgumentException("Middle name cannot be null or empty");
        }
        if (lName == null || lName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be null or empty");
        }
        this.fName = fName;
        this.mName = mName;
        this.lName = lName;
    }

    /**
     * Parses a full name string into components (first, middle, last).
     */
    public final void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        String[] splitName = name.trim().split("\\s+");
        if (splitName.length == 2) {
            setName(splitName[0], splitName[1]);
        } else if (splitName.length > 2) {
            StringBuilder middle = new StringBuilder();
            for (int i = 1; i < splitName.length - 1; i++) {
                middle.append(splitName[i]);
                if (i < splitName.length - 2) {
                    middle.append(" ");
                }
            }
            setName(splitName[0], middle.toString(), splitName[splitName.length - 1]);
        } else if (splitName.length == 1) {
            setName(splitName[0], null);
        } else {
            throw new IllegalArgumentException("Invalid name format");
        }
    }

    /**
     * Builds and returns the formatted street address from components.
     */
    public final String getStreetAddress() {
        // Build address parts conditionally
        java.util.List<String> parts = new java.util.ArrayList<>();
        // House number and modifier
        if (houseNumber > 0) {
            String hm = Integer.toString(houseNumber);
            if (houseModifier != null && !houseModifier.isEmpty()) {
                hm += houseModifier;
            }
            parts.add(hm);
        }
        // Street name
        if (streetName != null && !streetName.isEmpty()) {
            parts.add(toSentenceCase(streetName));
        }
        // City
        if (city != null && !city.isEmpty()) {
            parts.add(toSentenceCase(city));
        }
        // State
        if (state != null && !state.isEmpty()) {
            parts.add(state.toUpperCase());
        }
        // Zip code
        if (zipCode > 0) {
            parts.add(String.format("%05d", zipCode));
        }
        String fullAddress = String.join(" ", parts);
        // Unit type and number
        if (unitType != null && !unitType.isEmpty()) {
            fullAddress += ", " + toSentenceCase(unitType) + " " + unitNumber;
        }
        return fullAddress;
    }

    /**
     * Converts each word in a string to sentence case.
     */
    public final String toSentenceCase(String message) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] messages = message.trim().split("\\s+");

        for (int i = 0; i < messages.length; i++) {
            String word = messages[i];
            if (messages[i].contains("-")){
                String[] split = messages[i].split("-");
                StringBuilder splitBuilder = new StringBuilder();

                for (int j = 0; j < split.length; j++) {
                    split[j] = split[j].substring(0, 1).toUpperCase() + split[j].substring(1);
                    splitBuilder.append(split[j]);
                    if (j < split.length - 1){
                        splitBuilder.append("-");
                    }
                }
                messages[i] = splitBuilder.toString();
            }
            else if (!messages[i].equalsIgnoreCase(""))
                messages[i] = word.substring(0, 1).toUpperCase() + word.substring(1);

            stringBuilder.append(messages[i]);
            if (i < messages.length - 1){
                stringBuilder.append(" ");
            }
        }

        return stringBuilder.toString();
    }

    /**
     * Parses a raw address string and sets individual address components.
     */
    public final void setStreetAddress(String streetAddress) {
        if (streetAddress == null || streetAddress.trim().isEmpty()) {
            // Allow empty address
            this.streetName = "";
            this.city = "";
            this.state = "";
            this.zipCode = 0;
            this.unitType = null;
            this.unitNumber = 0;
            this.houseNumber = 0;
            this.houseModifier = "";
            return;
        }

        streetAddress = formatAddress(streetAddress);
        String[] address = streetAddress.split("\\s+");

        zipCode = setZipCode(address);
        unitType = setUnitType(address);
        state = setStateIndex(address);

        houseNumber = setHouseNo(address);
        streetName = setStreetName(address);
        city = setCityName(address);
        unitNumber = setUnitNumber(address);
    }


    /**
     * Normalizes address string by removing punctuation and converting to lowercase.
     */
    final String formatAddress(String streetAddress) {
        return streetAddress.trim().replaceAll("[^a-zA-Z0-9#\\-\\s]", "").toLowerCase();
    }


    /**
     * Parses and returns the zip code; returns 0 if none found.
     */
    public final int setZipCode(String[] address) {
        zipIndex = -1;
        for (int i = 1; i < address.length; i++) {
            if (address[i].matches("^\\d{5}$")) {
                zipIndex = i;
                break;
            }
        }
        if (zipIndex < 0) {
            // Allow empty zip code
            return 0;
        }
        return Integer.parseInt(address[zipIndex]);
    }

    /**
     * Parses and returns the state code; empty if none found.
     */
    public final String setStateIndex(String[] address) {
        stateIndex = -1;
        for (int i = 0; i < zipIndex; i++) {
            if (address[i].matches("[A-Za-z]{2}") && address[i + 1].matches("[0-9]{5}")) {
                stateIndex = i;
                break;
            }
        }
        if (stateIndex < 0) {
            // Allow empty state
            return "";
        }
        return address[stateIndex];
    }

    /**
     * Parses and returns the house number; sets houseModifier if present.
     */
    public final int setHouseNo(String[] address) {
        if (address.length == 0) {
            // Allow empty house number
            return 0;
        }

        String houseNo = address[0];
        houseModifier = null;
        int houseInt = -1;

        if (houseNo.matches("[0-9]+[A-Za-z]+")) {
            houseInt = Integer.parseInt(houseNo.replaceAll("[A-Za-z]+", ""));
            houseModifier = houseNo.replaceAll("[^A-Za-z]", "");
        } else if (houseNo.matches("[0-9]+")) {
            houseInt = Integer.parseInt(houseNo);
        } else {
            // Allow empty house number
            return 0;
        }

        return houseInt;
    }

    /**
     * Extracts and returns the street name from address components.
     */
    public final String setStreetName(String[] address) {
        streetTypeIndex = -1;
        int startIndex = 1;

        street_end:
        for (String type : STREET_TYPES) {
            for (int i = startIndex; i < address.length; i++) {
                if (address[i].replaceAll("[^A-Za-z]+", "").equalsIgnoreCase(type)) {
                    streetTypeIndex = i;
                    break street_end;
                }
            }
        }

        if (streetTypeIndex < 0) {
            // Allow empty street name
            return "";
        }

        return String.join(" ", Arrays.copyOfRange(address, startIndex, streetTypeIndex + 1));
    }

    /**
     * Extracts and returns the city name from address components.
     */
    public final String setCityName(String[] address) {
        if (streetTypeIndex < 0 || stateIndex < 0) {
            // Allow empty city name
            return "";
        }

        int cityIndex = streetTypeIndex + 1;

        return String.join(" ", Arrays.copyOfRange(address, cityIndex, stateIndex));
    }

    /**
     * Finds and returns the unit designator; null if none.
     */
    public final String setUnitType(String[] address) {
        // If no zip code parsed, skip unit parsing
        if (zipIndex < 0) {
            // Allow empty unit type
            return null;
        }

        String unitDesignator = null;
        designatorIndex = -1;

        // Search for unit designator after the zip code
        for (int i = zipIndex + 1; i < address.length; i++) {
            for (String designator : UNIT_DESIGNATORS) {
                if (address[i].replaceAll("[^A-Za-z#]+", "").equalsIgnoreCase(designator)) {
                    if (address[i].matches("[A-Za-z]+[0-9]+")) {
                        designatorIndex = i;
                        unitDesignator = address[i];
                        setUnitNumber(Integer.parseInt(address[i].replaceAll("[^0-9]", "")));
                        break;
                    } else if (address[i].matches("[A-Za-z#]+")) {
                        designatorIndex = i;
                        unitDesignator = address[i];
                        break;
                    }
                }
            }
        }
        return unitDesignator;
    }

    /**
     * Sets the unit number after validation.
     */
    public final void setUnitNumber(int unitNumber) {
        if (unitNumber < 0) {
            throw new IllegalArgumentException("Unit number must be a positive integer");
        }
        this.unitNumber = unitNumber;
    }

    /**
     * Parses and returns the unit number; returns 0 if none found.
     */
    public final int setUnitNumber(String[] address) {
        if (designatorIndex < 0 || designatorIndex + 1 >= address.length) {
            // Allow empty unit number
            return 0;
        }

        int unitNumberIndex = designatorIndex + 1;

        if (address[unitNumberIndex].matches("[0-9]+")) {
            return Integer.parseInt(address[unitNumberIndex]);
        }

        return 0;
    }

    public final int getId() {
        return this.id;
    }

    /**
     * Sets the unique ID after validating it is a 9-digit starting with 900.
     */
    public final void setId(int id) {
        if (id < 900000000 || id > 999999999) {
            throw new IllegalArgumentException("ID must start with 900 and be a valid 9-digit number");
        }
        this.id = id;
    }

    @Override
    public String toString() {
        return "Name: " + getName() + "\n ID: " + getId() + " \n Street Address: " + getStreetAddress();
    }

}