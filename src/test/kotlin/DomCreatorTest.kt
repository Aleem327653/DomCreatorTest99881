import org.example.DomCreator
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.coroutines.CoroutineContext

class DomCreatorTest{
    var domCreator:DomCreator=DomCreator
    val xmlFile = File("D:\\MohdAleem\\XmlTest\\src\\company.xml")

     @Test
     fun shouldGetAllEmployeeDetails(){
        val list= domCreator.fetchEmployeesFromXml(xmlFile)
         val emp1=list[0]
         val address1=emp1.address

         println(emp1)
         println(address1)
         assertTrue(list.size>0)

         assertEquals(emp1.id,list[0].id)
         assertEquals(emp1.age,list[0].age)
         assertEquals(emp1.name,list[0].name)
         assertEquals(emp1.department,list[0].department)

         assertTrue(address1.zip.isNotBlank())
         assertTrue(address1.city.isNotBlank())
         assertTrue(address1.street.isNotBlank())
     }


 @Test
 fun shouldLoadXmlDataToDomCreator(){
     val document=domCreator.parseXmlFile(xmlFile)
     val element= document.createElement("project")
     element.setAttribute("id","mytag")
     println(element.tagName)
     println(document.textContent)


     assertTrue(element.tagName.equals("project"))
     assertNotNull(document)
     assertNull(document.textContent)

 }

 }