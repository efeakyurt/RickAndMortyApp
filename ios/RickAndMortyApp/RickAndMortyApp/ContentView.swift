import SwiftUI

struct ContentView: View {
    @State private var characters: [Character] = []

    var body: some View {
        NavigationView {
            List {
                ForEach(characters) { character in
                    NavigationLink(destination: CharacterDetailView(characterId: character.id)) {
                        HStack {
                            AsyncImage(url: URL(string: character.image)) { image in
                                image.resizable().frame(width: 50, height: 50)
                            } placeholder: {
                                Color.gray.frame(width: 50, height: 50)
                            }
                            Text(character.name)
                        }
                    }
                }
            }
            .navigationTitle("Rick and Morty")
            .task {
                do {
                    characters = try await APIService.fetchCharacters()
                } catch {
                    print("Error fetching characters: \(error)")
                }
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
