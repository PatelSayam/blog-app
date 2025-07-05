import userContext from "../context/userContext";
import Base from "../components/Base";
import React, { useState } from "react";

const Services = () => {
  const [formData, setFormData] = useState({
    name: "",
    subject: "",
    message: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // Here you would typically handle the form submission, e.g., sending an email
    console.log("Form submitted:", formData);
  };

  

  const email = 'nityadhagatmirror@gmail.com';
  const subject = 'Inquiry or Support';
  const body = 'Hello, I need help with...'; 

  const gmailLink = `https://mail.google.com/mail/?view=cm&fs=1&to=${email}&su=${encodeURIComponent(subject)}&body=${encodeURIComponent(body)}`;


  return (
    <userContext.Consumer>
      {(object) => (
        <Base>
          <div style={{ margin: "20px"}}>
            <h1 style={{justifySelf: "center", marginBottom:"30px"}}>Meet Our Developers</h1>
            <div style={{ display: "flex", justifyContent: "center" }}>
              <div
                style={{
                  border: "1px solid #ccc",
                  padding: "10px",
                  width: "20%",
                }}
              >
                <h2>Developer 1</h2>
                <p>Name: Nitya Dhagat</p>
                <p>Skills: React, Springboot</p>
              </div>
              <div
                style={{
                  border: "1px solid #ccc",
                  padding: "10px",
                  width: "20%",
                }}
              >
                <h2>Developer 2</h2>
                <p>Name: Sayam Parejiya</p>
                <p>Skills: Spring Security, Springboot</p>
              </div>
            </div>

            <div style={{margin: "20px", justifySelf:"center"}}>
                <h1 style={{justifySelf:"center"}}>Contact Us</h1>
                <p>If you have any questions or need assistance, feel free to reach out to us via email:</p>
                <div style={{justifySelf:"center"}}>
                <a href={gmailLink} target="_blank" rel="noopener noreferrer" style={{ fontSize: "20px", color: "blue"}}>
                    Send us an email
                </a>
                </div>
            </div>

          </div>
        </Base>
      )}
    </userContext.Consumer>
  );
};

export default Services;
