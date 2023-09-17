//
//  NoveltyApp.swift
//  Novelty
//
//  Created by Samandar on 11/09/23.
//

import SwiftUI
import Common

@main
struct NoveltyApp: App {

    init() {
        InitKoinKt.doInitKoin(appDeclaration: {_ in })
    }

    var body: some Scene {
        WindowGroup {
            ZStack {
                Color(uiColor: UIColor(red: 0.0, green: 0.3137255, blue: 0.39607844, alpha: 1.0))
                 .ignoresSafeArea(.all)
                ComposeView()
            }.preferredColorScheme(.dark)
        }
    }
}
struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> some UIViewController {
        return MainKt.MainViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {
        print("update")
    }
}
