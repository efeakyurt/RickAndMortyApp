import Foundation

struct CharacterDetail: Decodable {
    let id: Int
    let name: String
    let image: String
    let status: String
    let species: String
    let gender: String
}
