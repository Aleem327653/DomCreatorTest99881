package org.example
import org.w3c.dom.*
import javax.xml.parsers.DocumentBuilderFactory
import java.io.File

fun main() {
    // Load and parse the XML document
    val xmlFile = File("D:\\MohdAleem\\XmlTest\\src\\company.xml")
    val document = parseXmlFile(xmlFile)

    // Fetch all employee elements
    val employees = document.getElementsByTagName("employee")

    // Loop through all employee nodes and extract details
    for (i in 0 until employees.length) {
        val employeeElement = employees.item(i) as Element

        // Fetching the ID attribute
        val id = employeeElement.getAttribute("id")

        // Fetching nested child elements like name, age, department, and address
        val name = getElementValue(employeeElement, "name")
        val age = getElementValue(employeeElement, "age")
        val department = getElementValue(employeeElement, "department")

        // Fetching address (which itself contains nested elements)
        val addressElement = employeeElement.getElementsByTagName("address").item(0) as Element
        val street = getElementValue(addressElement, "street")
        val city = getElementValue(addressElement, "city")
        val zip = getElementValue(addressElement, "zip")

        // Print the extracted employee details
        println("Employee ID: $id")
        println("Name: $name")
        println("Age: $age")
        println("Department: $department")
        println("Address: $street, $city, $zip")
        println("----")
    }
}

// Helper function to parse the XML file into a Document object
fun parseXmlFile(file: File): Document {
    val factory = DocumentBuilderFactory.newInstance()
    val builder = factory.newDocumentBuilder()
    return builder.parse(file)
}

// Helper function to get text content of an element by tag name
fun getElementValue(element: Element, tagName: String): String {
    val nodeList = element.getElementsByTagName(tagName)
    if (nodeList.length > 0) {
        return nodeList.item(0).textContent.trim()
    }
    return ""
}
