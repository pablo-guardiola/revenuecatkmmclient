import SwiftUI
import RevenueCatKMMClient

struct ContentView: View {
	private let revenuecatApiKey = Bundle.main.infoDictionary!["RevenueCatApiKey"] as! String
	let greet = "TODO"

	var body: some View {
        let test = testGetProjects()
		Text(greet)
	}
    
    func testGetProjects() {
        let httpClient = RevenueCatHttpClient(apiKey: revenuecatApiKey, staging: false)
        let revenueCatProjectsService = httpClient.createProjectsService()
        revenueCatProjectsService.getProjects() { result, arg  in
            if let error = result?.exceptionOrNull() {
                print("Error: \(error.message ?? "Unknown error")")
            } else {
                print("Success!")
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
