import Foundation

struct Character: Decodable, Identifiable {
    let id: Int
    let name: String
    let image: String
}

struct CharacterResponse: Decodable {
    let results: [Character]
}
