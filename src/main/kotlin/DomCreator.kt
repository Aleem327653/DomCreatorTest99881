package org.example

import org.w3c.dom.*
import javax.xml.parsers.DocumentBuilderFactory
import java.io.File

object DomCreator {

    // Helper function to parse XML file into Document object
    fun parseXmlFile(file: File): Document {
        val factory = DocumentBuilderFactory.newInstance()
        val builder = factory.newDocumentBuilder()
        return builder.parse(file)
    }

    // Function to fetch all employees from an XML file
    fun fetchEmployeesFromXml(file: File): List<Employee> {
        val document = parseXmlFile(file)
        val employeesNodeList = document.getElementsByTagName("employee")

        val employees = mutableListOf<Employee>()

        for (i in 0 until employeesNodeList.length) {
            val employeeElement = employeesNodeList.item(i) as Element

            // Extract attributes and elements for Employee and Address
            val id = employeeElement.getAttribute("id").toInt()
            val name = getElementValue(employeeElement, "name")
            val age = getElementValue(employeeElement, "age").toInt()
            val department = getElementValue(employeeElement, "department")

            // Extract the address
            val addressElement = employeeElement.getElementsByTagName("address").item(0) as Element
            val street = getElementValue(addressElement, "street")
            val city = getElementValue(addressElement, "city")
            val zip = getElementValue(addressElement, "zip")

            // Create Address object
            val address = Address(street, city, zip)

            // Create Employee object and add it to the list
            val employee = Employee(id, name, age, department, address)
            employees.add(employee)
        }

        return employees
    }

    // Helper function to get text content of an element by tag name
    private fun getElementValue(element: Element, tagName: String): String {
        val nodeList = element.getElementsByTagName(tagName)
        if (nodeList.length > 0) {
            return nodeList.item(0).textContent.trim()
        }
        return ""
    }
}



fun main() {
    // Path to the XML file
    val xmlFile = File("D:\\MohdAleem\\XmlTest\\src\\company.xml")

    // Fetch the list of employees from the XML file
    val employees = DomCreator.fetchEmployeesFromXml(xmlFile)

    // Print the fetched employees' details
    employees.forEach {
        println("Employee ID: ${it.id}")
        println("Name: ${it.name}")
        println("Age: ${it.age}")
        println("Department: ${it.department}")
        println("Address: ${it.address.street}, ${it.address.city}, ${it.address.zip}")
        println("----")
    }
}
