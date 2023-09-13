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
            ComposeView()
        }
    }
}
struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> some UIViewController {
        return MainKt.MainViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {

    }
}
