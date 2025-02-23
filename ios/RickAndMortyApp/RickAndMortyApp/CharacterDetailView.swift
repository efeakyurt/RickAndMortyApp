import SwiftUI

struct CharacterDetailView: View {
    let characterId: Int
    @State private var characterDetail: CharacterDetail?

    var body: some View {
        VStack {
            if let detail = characterDetail {
                Text(detail.name)
                    .font(.largeTitle)
                    .fontWeight(.bold)
                    .padding()

                AsyncImage(url: URL(string: detail.image)) { image in
                    image.resizable()
                        .frame(width: 200, height: 200)
                        .clipShape(RoundedRectangle(cornerRadius: 10))
                } placeholder: {
                    Color.gray.frame(width: 200, height: 200)
                }

                VStack(alignment: .leading, spacing: 10) {
                    Text("Name: \(detail.name)")
                    Text("Status: \(detail.status)")
                    Text("Species: \(detail.species)")
                    Text("Gender: \(detail.gender)")
                }
                .font(.title2)
                .padding()

                Spacer()
            } else {
                ProgressView("Loading...")
            }
        }
        .task {
            do {
                characterDetail = try await APIService.fetchCharacterDetail(id: characterId)
            } catch {
                print("Error fetching detail: \(error)")
            }
        }
    }
}
