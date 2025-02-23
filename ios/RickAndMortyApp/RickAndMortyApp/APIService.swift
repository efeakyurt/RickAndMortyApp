import Foundation

class APIService {
    static func fetchCharacters() async throws -> [Character] {
        let url = URL(string: "https://rickandmortyapi.com/api/character")!
        let (data, _) = try await URLSession.shared.data(from: url)
        let response = try JSONDecoder().decode(CharacterResponse.self, from: data)
        return response.results
    }

    static func fetchCharacterDetail(id: Int) async throws -> CharacterDetail {
        let url = URL(string: "https://rickandmortyapi.com/api/character/\(id)")!
        let (data, _) = try await URLSession.shared.data(from: url)
        return try JSONDecoder().decode(CharacterDetail.self, from: data)
    }
}
