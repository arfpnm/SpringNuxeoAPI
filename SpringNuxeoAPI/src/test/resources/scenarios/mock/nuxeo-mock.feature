Feature: Nuxeo Documents
  To look for the documents in Nuxeo and load it in Hippo
 
  Scenario: Look for documents in Nuxeo for a particular folder after setting  isChildrenRequired and checkMetaDataForChildrenRequired as "false"
    Given a url to check primary level folders and files "http://127.0.0.1:48080/nuxeo/api/v1/path/default-domain/sections/nhstrust/{0}/@children" 
    When I send a folder name "locations-and-contacts" to get all the documents from Nuxeo with isChildrenRequired as "true" and checkMetaDataForChildrenRequired as "true"
    Then the response should contain the document with title "NuxeoDocument1"
    And the "subfolder" must be empty
