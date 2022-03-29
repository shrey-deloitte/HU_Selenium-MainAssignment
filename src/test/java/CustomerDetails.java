public class CustomerDetails {
        private String firstName;
        private String lastName;
        private String pinCode;

        public CustomerDetails(String firstName, String lastName, String pinCode) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.pinCode = pinCode;
        }

        public CustomerDetails() {
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getPinCode() {
            return pinCode;
        }

        public void setPinCode(String pinCode) {
            this.pinCode = pinCode;
        }
    }

