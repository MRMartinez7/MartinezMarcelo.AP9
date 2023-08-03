Vue.createApp({
    data() {
        return {
            dni: "",
            firstName: "",
            lastName: "",
            outPut: "",
            clients: []
        }
    },
    created() {
        this.loadData();
    },
    methods: {
        // load and display JSON sent by server for /clients
        loadData() {
            axios.get("/clients")
                .then((response) => {
                    // handle success
                    this.outPut = response.data;
                    this.clients = response.data._embedded.clients;
                })
                .catch((error) => {
                    alert("Error loading clients: " + error)
                })
        },
        // handler for when user clicks add client
        addClient() {
            if (this.dni.length > 1 && this.firstName.length > 1 && this.lastName.length > 1) {
                this.postClient(this.dni, this.firstName, this.lastName);
            }
        },
        // code to post a new client using AJAX
        // on success, reload and display the updated data from the server
        postClient(dni, firstName, lastName) {
            axios.post("/clients", { "dni": dni, "firstName": firstName, "lastName": lastName })
                .then((response) => {
                    // handle success
                    this.loadData();
                    this.clearData();
                })
                .catch((error) => {
                    // handle error
                    alert("Error to create client: " + error)
                })
        },
        clearData() {
            this.firstName = "";
            this.lastName = "";
            this.dni = "";
        }
    }
}).mount("#app");