package com.mockservice.mockservice;

import org.springframework.http.MediaType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class MockserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MockserviceApplication.class, args);
    }

}

@RestController
class UserController {

    // Модель пользователя
    public static class User {
        private int id;
        private String email;
        private String firstName;
        private String lastName;
        private String address;
        private String createdAt;

        public User(int id, String email, String firstName, String lastName, String address) {
            this.id = id;
            this.email = email;
            this.firstName = firstName;
            this.lastName = lastName;
            this.address = address;
        }

        public User(int id, String email, String firstName, String lastName, String address, String createdAt) {
            this.id = id;
            this.email = email;
            this.firstName = firstName;
            this.lastName = lastName;
            this.address = address;
            this.createdAt = createdAt;
        }

        public User(String email, String firstName, String lastName, String address) {
            this.email = email;
            this.firstName = firstName;
            this.lastName = lastName;
            this.address = address;
        }

        public User() {
        }

        @JsonProperty("id")
        public int getId() {
            return id;
        }

        @JsonProperty("email")
        public String getEmail() {
            return email;
        }

        @JsonProperty("first_name")
        public String getFirstName() {
            return firstName;
        }

        @JsonProperty("last_name")
        public String getLastName() {
            return lastName;
        }

        @JsonProperty("address")
        public String getAddress() {
            return address;
        }

        @JsonProperty("createdAt")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public String getCreatedAt() {
            return createdAt;
        }
    }

    // массивы имён, фамилий и адресов для генерации данных пользователей
    public String[] firstNames = { "Aaron", "Abraham", "Adam", "Adrian", "Aidan", "Alan", "Albert", "Alejandro", "Alex",
            "Alexander", "Alfred", "Andrew", "Angel", "Anthony", "Antonio", "Ashton", "Austin", "Aaliyah", "Abigail",
            "Ada", "Adelina", "Agatha", "Alexa", "Alexandra", "Alexis", "Alise", "Allison", "Alyssa", "Amanda", "Amber",
            "Amelia", "Angelina", "Anita", "Ann", "Ariana", "Arianna", "Ashley", "Audrey", "Autumn", "Ava", "Avery",
            "B", "Benjamin", "Bernard", "Blake", "Brandon", "Brian", "Bruce", "Bryan", "Bailey", "Barbara", "Beatrice",
            "Belinda", "Brianna", "Bridjet", "Brooke", "Cameron", "Carl", "Carlos", "Charles", "Christopher", "Cole",
            "Connor", "Caleb", "Carter", "Chase", "Christian", "Clifford", "Cody", "Colin", "Curtis", "Cyrus",
            "Caroline", "Catherine", "Cecilia", "Celia", "Chloe", "Christine", "Claire", "Daniel", "David", "Dennis",
            "Devin", "Diego", "Dominic", "Donald", "Douglas", "Dylan", "Daisy", "Danielle", "Deborah", "Delia",
            "Destiny", "Diana", "Dorothy", "Edward", "Elijah", "Eric", "Ethan", "Evan", "Eleanor", "Elizabeth", "Ella",
            "Emily", "Emma", "Erin", "Evelyn", "Francis", "Fred", "Faith", "Fiona", "Florence", "Freda", "Gabriel",
            "Gavin", "Geoffrey", "George", "Gerld", "Gilbert", "Gordon", "Graham", "Gregory", "Gloria", "Gabriella",
            "Gabrielle", "Gladys", "Grace", "Harold", "Harry", "Hayden", "Henry", "Herbert", "Horace", "Howard", "Hugh",
            "Hunter", "Hailey", "Haley", "Hannah", "Helen", "Ian", "Isaac", "Isaiah", "Isabel", "Isabella", "Jack",
            "Jackson", "Jacob", "Jaden", "Jake", "James", "Jason", "Jayden", "Jeffery", "Jeremiah", "Jesse", "Jesus",
            "John", "Jonathan", "Jordan", "Jose", "Joseph", "Joshua", "Juan", "Julian", "Justin", "Jacqueline", "Jada",
            "Jane", "Jasmine", "Jenna", "Jennifer", "Jessica", "Jocelyn", "Jordan", "Josephine", "Joyce", "Julia",
            "Keith", "Kevin", "Kyle", "Kaitlyn", "Katelyn", "Katherine", "Kathryn", "Kayla", "Kaylee", "Kimberly",
            "Kylie", "Landon", "Lawrence", "Leonars", "Lewis", "Logan", "Louis", "Lucas", "Luke", "Laura", "Lauren",
            "Leah", "Leonora", "Leslie", "Lillian", "Lily", "Linda", "Lorna", "Luccile", "Lucy", "Lynn", "Malcolm",
            "Martin", "Mason", "Matthew", "Michael", "Miguel", "Miles", "Morgan", "Mabel", "Mackenzie", "Madeline",
            "Madison", "Makayla", "Margaret", "Maria", "Marisa", "Marjorie", "Mary", "Maya", "Megan", "Melanie",
            "Melissa", "Mia", "Michelle", "Mildred", "Molly", "Monica", "Nathan", "Nathaniel", "Neil", "Nicholas",
            "Noah", "Norman", "Nancy", "Natalie", "Nicole", "Nora", "Oliver", "Oscar", "Oswald", "Owen", "Olivia",
            "Patrick", "Peter", "Philip", "Paige", "Pamela", "Patricia", "Pauline", "Penelope", "Priscilla", "Ralph",
            "Raymond", "Reginald", "Richard", "Robert", "Rodrigo", "Roger", "Ronald", "Ryan", "Rachel", "Rebecca",
            "Riley", "Rita", "Rosalind", "Rose", "Samuel", "Sean", "Sebastian", "Seth", "Simon", "Stanley", "Steven",
            "Samantha", "Sandra", "Sara", "Sarah", "Savannah", "Sharon", "Sheila", "Shirley", "Sierra", "Sofia",
            "Sophia", "Stephanie", "Susan", "Sybil", "Sydney", "Sylvia", "Thomas", "Timothy", "Tyler", "Taylor",
            "Trinity", "Vanessa", "Victoria", "Violet", "Virginia", "Wallace", "Walter", "William", "Wyatt", "Winifred",
            "Xavier", "Yvonne", "Zachary", "Zoe" };
    public String[] lastNames = { "Abramson", "Adamson", "Adderiy", "Addington", "Adrian", "Albertson", "Aldridge",
            "Allford", "Alsopp", "Anderson", "Andrews", "Archibald", "Arnold", "Arthurs", "Atcheson", "Attwood",
            "Audley", "Austin", "Ayrton", "Babcock", "Backer", "Baldwin", "Bargeman", "Barnes", "Barrington",
            "Bawerman", "Becker", "Benson", "Berrington", "Birch", "Bishop", "Black", "Blare", "Blomfield", "Boolman",
            "Bootman", "Bosworth", "Bradberry", "Bradshaw", "Brickman", "Brooks", "Brown", "Bush", "Calhoun",
            "Campbell", "Carey", "Carrington", "Carroll", "Carter", "Chandter", "Chapman", "Charlson", "Chesterton",
            "Clapton", "Clifford", "Coleman", "Conors", "Cook", "Cramer", "Creighton", "Croftoon", "Crossman",
            "Daniels", "Davidson", "Day", "Dean", "Derrick", "Dickinson", "Dodson", "Donaldson", "Donovan", "Douglas",
            "Dowman", "Dutton", "Duncan", "Dunce", "Durham", "Dyson", "Eddington", "Edwards", "Ellington", "Elmers",
            "Enderson", "Erickson", "Evans", "Faber", "Fane", "Farmer", "Farrell", "Ferguson", "Finch", "Fisher",
            "Fitzgerald", "Flannagan", "Flatcher", "", "Fleming", "Ford", "Forman", "Forster", "Foster", "Francis",
            "Fraser", "Freeman", "Fulton", "Galbraith", "Gardner", "Garrison", "Gate", "Gerald", "Gibbs", "Gilbert",
            "Gill", "Gilmore", "Gilmore", "Gimson", "Goldman", "Goodman", "Gustman", "Haig", "Hailey", "Hamphrey",
            "Hancock", "Hardman", "Harrison", "Hawkins", "Higgins", "Hodges", "Hoggarth", "Holiday", "Holmes", "Howard",
            "Jacobson", "James", "Jeff", "Jenkin", "Jerome", "Johnson", "Jones", "Keat", "Kelly", "Kendal", "Kennedy",
            "Kennett", "Kingsman", "Kirk", "Laird", "Lamberts", "Larkins", "Lawman", "Leapman", "Leman", "Lewin",
            "Little", "Livingston", "Longman", "MacAdam", "MacAlister", "MacDonald", "Macduff", "Macey", "Mackenzie",
            "Mansfield", "Marlow", "Marshman", "Mason", "Mathews", "Mercer", "Michaelson", "Miers", "Miller", "Miln",
            "Milton", "Molligan", "Morrison", "Murphy", "Nash", "Nathan", "Neal", "Nelson", "Nevill", "Nicholson",
            "Nyman", "Oakman", "Ogden", "Oldman", "Oldridge", "Oliver", "Osborne", "Oswald", "Otis", "Owen", "Page",
            "Palmer", "Parkinson", "Parson", "Pass", "Paterson", "Peacock", "Pearcy", "Peterson", "Philips", "Porter",
            "Quincy", "Raleigh", "Ralphs", "Ramacey", "Reynolds", "Richards", "Roberts", "Roger", "Russel", "Ryder",
            "Salisburry", "Salomon", "Samuels", "Saunder", "Shackley", "Sheldon", "Sherlock", "Shorter", "Simon",
            "Simpson", "Smith", "Stanley", "Stephen", "Stevenson", "Sykes", "Taft", "Taylor", "Thomson", "Thorndike",
            "Thornton", "Timmons", "Tracey", "Turner", "Vance", "Vaughan", "Wainwright", "Walkman", "Wallace", "Waller",
            "Walter", "Ward", "Warren", "Watson", "Wayne", "Webster", "Wesley", "White", "WifKinson", "Winter", "Wood",
            "Youmans", "Young" };
    public String[] addresses = { "Main Street, New York", "Lenin Street, Moscow", "Maple Avenue, London",
            "Washington Blvd, LA", "Ocean Drive, Miami" };

    private User generateRandomUser(int id) {

        // возвращает рандомно сгенерированного пользователя User(id, email, firstName,
        // lastName, address)

        Random random = new Random();
        int house = random.nextInt(149);
        String firstName = firstNames[random.nextInt(firstNames.length)];
        String lastName = lastNames[random.nextInt(lastNames.length)];
        String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + house + "@reqres.in";
        // переменная house для добавления случайного числа в адрес почты
        String address = house + " " + addresses[random.nextInt(addresses.length)];
        return new User(id, email, firstName, lastName, address);
    }

    @GetMapping("/api/users") // эмулирует возвращение массива пользователей, где количество пользователей
                              // соответствует параметру count
    public List<User> getUsers(@RequestParam(value = "count", defaultValue = "1") int count) {
        List<User> users = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            users.add(generateRandomUser(i));
        }
        return users;
    }

    @GetMapping("/api/user/{id}") // эмулирует возвращение данные о пользователе с указанным id
    public User getUserById(@PathVariable int id) {
        return generateRandomUser(id);
    }

    @PostMapping(value = "/api/user", consumes = MediaType.APPLICATION_JSON_VALUE) // эмулирует создание нового
                                                                                   // пользователя с помощью JSON, в
                                                                                   // результате возвращая данные нового
                                                                                   // пользователя, где "first_name",
                                                                                   // "last_name", "email", "address"
                                                                                   // совпадают с теми, которые пришли в
                                                                                   // запросе, id генерируется рандомно
                                                                                   // и в "createdAt" возвращается
                                                                                   // текущая дата
    @ResponseStatus(code = org.springframework.http.HttpStatus.CREATED)
    public User createUser(@RequestBody User newUser) {
        Random random = new Random();
        int newId = random.nextInt(10000);
        String createdAt = LocalDateTime.now().toString(); // возвращается текущая дата
        return new User(newId, newUser.getEmail(), newUser.getFirstName(), newUser.getLastName(), newUser.getAddress(),
                createdAt);
    }
}
