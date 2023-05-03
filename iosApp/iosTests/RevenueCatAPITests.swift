import XCTest
import RevenueCatKMMClient

class RevenueCatAPITests: XCTestCase {
    
    private let revenuecatApiKey = Bundle.main.infoDictionary!["RevenueCatApiKey"] as! String
    private lazy var httpClient = RevenueCatHttpClient(apiKey: revenuecatApiKey, staging: false)

    override func setUpWithError() throws {
        // Put setup code here. This method is called before the invocation of each test method in the class.
    }

    override func tearDownWithError() throws {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
    }

    func testGetProjects() {
        let revenueCatProjectsService = httpClient.createProjectsService()
        let expectation = expectation(description: "getProjects should return a result.")
        revenueCatProjectsService.getProjects() { result, arg  in
            XCTAssertNotNil(result)
            expectation.fulfill()
        }
        waitForExpectations(timeout: 10.0)
    }

    func testPerformanceExample() throws {
        // This is an example of a performance test case.
        measure {
            // Put the code you want to measure the time of here.
        }
    }

}
