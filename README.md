# Swiggy Automation with Selenium

This project automates a real-world user flow on [Swiggy](https://www.swiggy.com) using Java and Selenium WebDriver. It performs login, restaurant search, cart operations, address addition, and proceeds to payment — mimicking a real user's journey.

---

## Features

- Automated login via mobile number and manual OTP
- Location selection (Bengaluru)
- Search and select "Meghana Foods"
- Add an item to the cart and increase its quantity
- Add a delivery address with landmark and address type
- Click "Proceed to Pay"
- Takes screenshots at important steps

---

## Tech Stack

- Language: Java  
- Automation Tool: Selenium WebDriver  
- Build Tool: Maven (optional)  
- IDE: Eclipse  
- Browser: Google Chrome  
- OS: macOS  

---

## How to Run

### Prerequisites

- Java 17 or above
- Google Chrome installed
- ChromeDriver matching your Chrome version
- Eclipse or any other Java IDE

### Setup Instructions

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/swiggy-automation.git
   cd swiggy-automation

##Notes
1.OTP input is manual (the script waits for 45 seconds).
2.Web elements may change if Swiggy updates their site. Update locators accordingly.
3.This project is intended for learning and testing purposes only.

