package Week3;

import java.util.Arrays;

public class Person {
    private String fName, mName, lName, streetName, city, state, unitType, houseModifier;;
    private int id, houseNumber, zipCode, unitNumber;
    private int streetTypeIndex = -1, stateIndex = -1, zipIndex = -1, designatorIndex = -1;

    final String[] STREET_TYPES = {"ALLEY", "ALY", "ANNEX", "ANX", "ARCADE", "ARC", "AVENUE", "AVE", "BAYOU", "BYU", "BEACH", "BCH", "BEND", "BND", "BLUFF", "BLF", "BOTTOM", "BTM", "BOULEVARD", "BLVD", "BRANCH", "BR", "BRIDGE", "BRG", "BROOK", "BRK", "BURG", "BG", "BYPASS", "BYP", "CAMP", "CP", "CANYON", "CYN", "CAPE", "CPE", "CAUSEWAY", "CSWY", "CENTER", "CTR", "CIRCLE", "CIR", "CLIFF", "CLF", "CLUB", "CLB", "COMMON", "CMN", "CORNER", "COR", "COURSE", "CRSE", "COURT", "CT", "COVE", "CV", "CREEK", "CRK", "CRESCENT", "CRES", "CREST", "CRST", "CROSSING", "XING", "DALE", "DL", "DAM", "DM", "DIVIDE", "DV", "DRIVE", "DR", "ESTATE", "EST", "EXPRESSWAY", "EXPY", "EXTENSION", "EXT", "FALLS", "FLS", "FERRY", "FRY", "FIELD", "FLD", "FLAT", "FLT", "FORD", "FRD", "FOREST", "FRST", "FORGE", "FRG", "FORK", "FRK", "FORT", "FT", "FREEWAY", "FWY", "GARDEN", "GDN", "GATEWAY", "GTWY", "GLEN", "GLN", "GREEN", "GRN", "GROVE", "GRV", "HARBOR", "HBR", "HAVEN", "HVN", "HEIGHTS", "HTS", "HIGHWAY", "HWY", "HILL", "HL", "HOLLOW", "HOLW", "INLET", "INLT", "ISLAND", "IS", "ISLE", "ISLE", "JUNCTION", "JCT", "KEY", "KY", "KNOLL", "KNL", "LAKE", "LK", "LAND", "LAND", "LANDING", "LNDG", "LANE", "LN", "LIGHT", "LGT", "LOAF", "LF", "LOCK", "LCK", "LODGE", "LDG", "LOOP", "LOOP", "MALL", "MALL", "MANOR", "MNR", "MEADOW", "MDW", "MEWS", "MEWS", "MILL", "ML", "MISSION", "MSN", "MOTORWAY", "MTWY", "MOUNT", "MT", "MOUNTAIN", "MTN", "NECK", "NCK", "ORCHARD", "ORCH", "OVAL", "OVAL", "PARK", "PARK", "PARKWAY", "PKWY", "PASS", "PASS", "PATH", "PATH", "PIKE", "PIKE", "PINE", "PNE", "PLACE", "PL", "PLAIN", "PLN", "PLAZA", "PLZ", "POINT", "PT", "PORT", "PRT", "PRAIRIE", "PR", "RADIAL", "RADL", "RANCH", "RNCH", "RAPID", "RPD", "REST", "RST", "RIDGE", "RDG", "RIVER", "RIV", "ROAD", "RD", "ROW", "ROW", "RUN", "RUN", "SHORE", "SHR", "SPRING", "SPG", "SPUR", "SPUR", "SQUARE", "SQ", "STATION", "STA", "STRAVENUE", "STRA", "STREAM", "STRM", "STREET", "ST", "SUMMIT", "SMT", "TERRACE", "TER", "TRACE", "TRCE", "TRACK", "TRAK", "TRAFFICWAY", "TRFY", "TRAIL", "TRL", "TUNNEL", "TUNL", "TURNPIKE", "TPKE", "UNDERPASS", "UPAS", "UNION", "UN", "VALLEY", "VLY", "VIADUCT", "VIA", "VIEW", "VW", "VILLAGE", "VLG", "VILLE", "VL", "VISTA", "VIS", "WALK", "WALK", "WALL", "WALL", "WAY", "WAY", "WELL", "WL", "WOOD", "WD", "WOODS", "WDS"};
    final String[] UNIT_DESIGNATORS = {"Apartment", "Apt.", "Apt", "Suite", "Ste.", "Ste", "Unit", "Building", "Bldg.", "Bldg", "Floor", "Fl.", "Fl", "Room", "Rm.", "Rm", "Department", "Dept.", "Dept", "Number", "No.", "No", "#", "Lot", "Trailer", "Trlr.", "Trlr", "Pier", "#"};

    Person(int id, String name, String fullAddress) {
        this.setId(id);
        this.setName(name);
        this.setStreetAddress(fullAddress);
    }

    Person() {
        this.setId(900000000);
        this.setName("John", "Doe");
        this.setStreetAddress("123 Main Street City ST 99999, Apt 232");
    }

    public String getName() {
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

    public void setName(String fName, String lName) {
        this.fName = fName;
        mName = null;
        this.lName = lName;
    }

    public void setName(String fName, String mName, String lName){
        this.fName = fName;
        this.mName = mName;
        this.lName = lName;
    }

    public void setName(String name) {
        String[] splitName = name.trim().split("\\s+");
        if (splitName.length == 2){
            setName(splitName[0], splitName[1]);
        } else if (splitName.length > 2){
            StringBuilder middle = new StringBuilder();
            for (int i = 1; i < splitName.length - 1; i++){
                middle.append(splitName[i]);
                if (i < splitName.length - 2){
                    middle.append(" ");
                }
            }
            setName(splitName[0], middle.toString(), splitName[splitName.length-1]);
        } else if (splitName.length == 1) {
            setName(splitName[0],null);
        }
    }

    public String getStreetAddress() {
        String fullAddress;

        if (houseModifier == null){
            houseModifier = "";
        }

        fullAddress = houseNumber + houseModifier + " " + toSentenceCase(streetName) + " " + toSentenceCase(city) + " " + state.toUpperCase() + " " + String.format("%05d", zipCode);

        if (unitType != null) {
            fullAddress += ", " + toSentenceCase(unitType) + " " + unitNumber;
        }

        return fullAddress;
    }

    public String toSentenceCase(String message) {
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

    public void setStreetAddress(String streetAddress) {
        streetAddress = formatAddress(streetAddress);
        String[] address = formatAddress(streetAddress).split("\\s+");

        zipCode = setZipCode(address);
        unitType = setUnitType(address);
        state = setStateIndex(address);

        houseNumber = setHouseNo(address);
        streetName = setStreetName(address);
        city = setCityName(address);
        unitNumber = setUnitNumber(address);
    }


    String formatAddress(String streetAddress) {
        return streetAddress.trim().replaceAll("[^a-zA-Z0-9#\\-\\s]", "").toLowerCase();
    }


    public int setZipCode(String[] address) {
        zipIndex = -1;
        for (int i = 1; i < address.length; i++) {
            if (address[i].matches("^\\d{5}$")) {
                zipIndex = i;
            }
        }
        if (zipIndex < 0) {
            throw new IllegalArgumentException("No zipcode found. Please enter a five digit zip code.");
        }
        return Integer.parseInt(address[zipIndex]);
    }

    int setHouseNo(String[] address) {
        String houseNo = address[0];
        houseModifier = null;
        int houseInt = -1;

        if (houseNo.matches("[0-9]+[A-Za-z]+")) {
            houseInt = Integer.parseInt(houseNo.replaceAll("[A-Za-z]+", ""));
            houseModifier = houseNo.replaceAll("[^A-Za-z]", "");
        } else if (houseNo.matches("[0-9]+")) {
            houseInt = Integer.parseInt(houseNo);
        } else {
            throw new IllegalArgumentException("Invalid house number format");
        }

        return houseInt;
    }

    String setStreetName(String[] address) {
        streetTypeIndex = -1;
        int startIndex = 1;

        street_end:
        for (String type : STREET_TYPES) {
            for (int i = startIndex; i < address.length; i++) {
                if (address[i].replaceAll("[^A-Za-z]+","").equalsIgnoreCase(type)) {
                    streetTypeIndex = i;
                    break street_end;
                }
            }
        }

        if (streetTypeIndex < 0) {
            throw new IllegalArgumentException("Street address could not be extracted");
        }

        return String.join(" ", Arrays.copyOfRange(address, startIndex, streetTypeIndex + 1));
    }

    String setCityName(String[] address) {
        int cityIndex = streetTypeIndex + 1;

        return String.join(" ", Arrays.copyOfRange(address, cityIndex, stateIndex));
    }

    public String setStateIndex(String[] address) {
        stateIndex = -1;
        for (int i = 0; i < zipIndex; i++) {
            if (address[i].matches("[A-Za-z]{2}") && address[i + 1].matches("[0-9]{5}")) {
                stateIndex = i;
                break;
            }
        }
        if (stateIndex < 0) {
            throw new IllegalArgumentException("State index could not be set");
        }
        return address[stateIndex];
    }

    String setUnitType(String[] address){
        String unitDesignator = null;
        designatorIndex = -1;

        for (int i = zipIndex; i < address.length; i++) {
            for(String designator: UNIT_DESIGNATORS){
                if (address[i].replaceAll("[^A-Za-z#]+","").equalsIgnoreCase(designator)){
                    if (address[i].matches("[A-Za-z]+[0-9]+")){
                        designatorIndex = i;
                        unitDesignator = address[i];
                        setUnitNumber(Integer.parseInt(address[i].replaceAll("[^0-9]","")));
                        break;
                    } else if (address[i].matches("[A-Za-z#]+")){
                        designatorIndex = i;
                        unitDesignator = address[i];
                        break;
                    }
                }
            }
        }
        return unitDesignator;
    }

    void setUnitNumber(int unitNumber){
        this.unitNumber = unitNumber;
    }

    int setUnitNumber(String[] address){
        int unitNumberIndex = designatorIndex + 1, unitNumber = -1;

        if (designatorIndex > 0 && address[unitNumberIndex].matches("[0-9]+")){
            unitNumber = Integer.parseInt(address[unitNumberIndex]);
        }

        return unitNumber;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        int firstThree = id / 1000000;
        if (firstThree != 900) {
            System.out.print("ID must start with 900");
        } else {
            this.id = id;
        }
    }

    @Override
    public String toString() {
        return "Name: " + getName() + "\n ID: " + getId() + " \n Street Address: " + getStreetAddress();
    }

}
