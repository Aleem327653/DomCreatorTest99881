import org.example.getElementValue
import org.example.parseXmlFile
import org.junit.jupiter.api.Assertions.*


import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.NodeList
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory

class MainKtTest{

    // Test for parsing XML file
    @Test
    fun `test parseXmlFile should return a valid Document`() {
        val xmlFile = File("D:\\MohdAleem\\XmlTest\\src\\company.xml")// Path to the XML file
        val document = parseXmlFile(xmlFile)
        println(document.textContent)
        // Assert that the document is not null
        assertNotNull(document)
    }

    // Test for extracting element values
    @Test
    fun `test getElementValue should return correct value for existing tag`() {
        // Mock an XML element for testing
        val xml = """
            <employee>
                <name>John Doe</name>
                <age>30</age>
                <department>Engineering</department>
            </employee>
        """.trimIndent()

        val document = parseXmlString(xml)
        val employeeElement = document.getElementsByTagName("employee").item(0) as Element

        // Test the getElementValue function
        val name = getElementValue(employeeElement, "name")
        assertEquals("John Doe", name)

        val age = getElementValue(employeeElement, "age")
        assertEquals("30", age)

        val department = getElementValue(employeeElement, "department")
        assertEquals("Engineering", department)
    }

    // Test for extracting value of a non-existing tag
    @Test
    fun `test getElementValue should return empty string for non-existing tag`() {
        // Mock an XML element for testing
        val xml = """
            <employee>
                <name>John Doe</name>
                <age>30</age>
            </employee>
        """.trimIndent()

        val document = parseXmlString(xml)
        val employeeElement = document.getElementsByTagName("employee").item(0) as Element

        // Test non-existing tag value
        val nonExistingTag = getElementValue(employeeElement, "non-existing-tag")
        assertEquals("", nonExistingTag)
    }

    // Test for parsing and extracting values from real XML
    @Test
    fun `test employee extraction should fetch correct employee data from XML`() {
        val xmlFile = File("D:\\MohdAleem\\XmlTest\\src\\company.xml")
        val document = parseXmlFile(xmlFile)
        val employees = document.getElementsByTagName("employee")

        // Check the number of employees in the XML
        assertEquals(2, employees.length)

        // Test the first employee
        val firstEmployee = employees.item(0) as Element
        assertEquals("1", firstEmployee.getAttribute("id"))
        assertEquals("John Doe", getElementValue(firstEmployee, "name"))
        assertEquals("30", getElementValue(firstEmployee, "age"))
        assertEquals("Engineering", getElementValue(firstEmployee, "department"))

        // Test the address of the first employee
        val addressElement = firstEmployee.getElementsByTagName("address").item(0) as Element
        assertEquals("123 Main St", getElementValue(addressElement, "street"))
        assertEquals("Metropolis", getElementValue(addressElement, "city"))
        assertEquals("12345", getElementValue(addressElement, "zip"))
    }

    // Helper function to parse XML from string (for testing)
    fun parseXmlString(xml: String): Document {
        val factory = DocumentBuilderFactory.newInstance()
        val builder = factory.newDocumentBuilder()
        return builder.parse(xml.byteInputStream())
    }
}
