function validateForm() {
    const name = document.getElementById("name").value;
    const email = document.getElementById("email").value;
    const event = document.getElementById("event").value;

    if (!name || !email || !event) {
        alert("All fields are required!");
        return false;
    }
    return true;
}
